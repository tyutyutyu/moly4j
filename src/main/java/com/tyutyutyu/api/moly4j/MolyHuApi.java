package com.tyutyutyu.api.moly4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tyutyutyu.api.moly4j.author.AuthorWrapper;
import com.tyutyutyu.api.moly4j.book.BookWrapper;
import com.tyutyutyu.api.moly4j.book.ResultById;
import com.tyutyutyu.api.moly4j.book_by_isbn.ResultByISBN;
import com.tyutyutyu.api.moly4j.book_citations.Citation;
import com.tyutyutyu.api.moly4j.book_citations.CitationsWrapper;
import com.tyutyutyu.api.moly4j.book_reviews.Review;
import com.tyutyutyu.api.moly4j.book_reviews.ReviewsWrapper;
import com.tyutyutyu.api.moly4j.books.BooksWrapper;
import com.tyutyutyu.api.moly4j.books.ResultByQuery;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MolyHuApi {

	private static final MolyHuApi INSTANCE = new MolyHuApi();

	private static final String SEARCH_BY_QUERY_URL = "http://moly.hu/api/books.json";

	private static final String GET_BY_ISBN_URL = "http://moly.hu/api/book_by_isbn.json";

	private static final String GET_BY_ID_URL = "http://moly.hu/api/book/{id}.json";

	private static final String GET_AUTHOR_BY_ID_URL = "http://moly.hu/api/author/{id}.json";

	private static final String GET_REVIEWS_BY_ID_URL = "http://moly.hu/api/book_reviews/{id}.json";

	private static final String GET_CITATIONS_BY_ID_URL = "http://moly.hu/api/book_citations/{id}.json";

	private final ObjectMapper mapper = new ObjectMapper();

	private MolyHuApi() {

	}

	public static MolyHuApi getInstance() {

		return INSTANCE;
	}

	/**
	 * Könyvkereső
	 *
	 * @param q
	 *            Keresőkifejezés
	 * @return Találati lista
	 * @throws MolyHuException
	 *             Hiba esetén
	 */
	public List<ResultByQuery> searchByQuery(String q) throws MolyHuException {

		String jsonResponse;
		try {
			// @formatter:off
			jsonResponse = Unirest.get(SEARCH_BY_QUERY_URL)
					.queryString("q", StringUtils.stripAccents(q))
					.asString()
					.getBody();
			// @formatter:on
		} catch (final UnirestException e) {
			throw new MolyHuException(e);
		}

		BooksWrapper result;
		try {
			result = mapper.readValue(jsonResponse, BooksWrapper.class);
		} catch (final IOException e) {
			throw new MolyHuException(e);
		}

		log.debug("result: {}", result);
		return result.getBooks();
	}

	/**
	 * ISBN-kereső
	 *
	 * @param q
	 *            Keresőkifejezés
	 * @return Találat
	 * @throws MolyHuException
	 *             Hiba esetén
	 */
	public ResultByISBN getByISBN(String q) throws MolyHuException {

		String jsonResponse;
		try {
			// @formatter:off
			jsonResponse = Unirest.get(GET_BY_ISBN_URL)
					.queryString("q", q)
					.asString()
					.getBody();
			// @formatter:on
		} catch (final UnirestException e) {
			throw new MolyHuException(e);
		}

		if ("".equals(jsonResponse.trim())) {
			// TODO: null or exception?
			return null;
		}

		ResultByISBN result;
		try {
			result = mapper.readValue(jsonResponse, ResultByISBN.class);
		} catch (final IOException e) {
			throw new MolyHuException(e);
		}

		log.debug("result: {}", result);
		return result;
	}

	/**
	 * Könyvadatok
	 *
	 * @param id
	 *            Könyv azonosító
	 * @return Találat
	 * @throws MolyHuException
	 *             Hiba esetén
	 */
	public ResultById getById(int id) throws MolyHuException {

		String jsonResponse;
		try {
			// @formatter:off
			jsonResponse = Unirest.get(GET_BY_ID_URL)
					.routeParam("id", id + "")
					.asString()
					.getBody();
			// @formatter:on
		} catch (final UnirestException e) {
			throw new MolyHuException(e);
		}

		BookWrapper result;
		try {
			result = mapper.readValue(jsonResponse, BookWrapper.class);
		} catch (final IOException e) {
			throw new MolyHuException(e);
		}

		log.debug("result: {}", result);
		return result.getBook();
	}

	/**
	 * Szerzőadatok
	 *
	 * @param id
	 *            Szerző azonosító
	 * @return Szerző adatok
	 * @throws MolyHuException
	 *             Hiba esetén
	 */
	public com.tyutyutyu.api.moly4j.author.Author getAuthorById(int id) throws MolyHuException {

		String jsonResponse;
		try {
			// @formatter:off
			jsonResponse = Unirest.get(GET_AUTHOR_BY_ID_URL)
					.routeParam("id", id + "")
					.asString()
					.getBody();
			// @formatter:on
		} catch (final UnirestException e) {
			throw new MolyHuException(e);
		}

		AuthorWrapper result;
		try {
			result = mapper.readValue(jsonResponse, AuthorWrapper.class);
		} catch (final IOException e) {
			throw new MolyHuException(e);
		}

		log.debug("result: {}", result);
		return result.getAuthor();
	}

	/**
	 * Értékelések
	 *
	 * @param id
	 *            Könyv azonosító
	 * @param page
	 *            Oldalszám
	 * @return Értékelések
	 * @throws MolyHuException
	 *             Hiba esetén
	 */
	public List<Review> getBookReviews(int id, int page) throws MolyHuException {

		String jsonResponse;
		try {
			// @formatter:off
			jsonResponse = Unirest.get(GET_REVIEWS_BY_ID_URL)
					.routeParam("id", id + "")
					.queryString("page", page)
					.asString()
					.getBody();
			// @formatter:on
		} catch (final UnirestException e) {
			throw new MolyHuException(e);
		}

		ReviewsWrapper result;
		try {
			result = mapper.readValue(jsonResponse, ReviewsWrapper.class);
		} catch (final IOException e) {
			throw new MolyHuException(e);
		}

		log.debug("result: {}", result);
		return result.getReviews();
	}

	/**
	 * Idézetek
	 *
	 * @param id
	 *            Könyv azonosító
	 * @param page
	 *            Oldalszám
	 * @return Idézetek
	 * @throws MolyHuException
	 *             Hiba esetén
	 */
	public List<Citation> getBookCitations(int id, int page) throws MolyHuException {

		String jsonResponse;
		try {
			// @formatter:off
			jsonResponse = Unirest.get(GET_CITATIONS_BY_ID_URL)
					.routeParam("id", id + "")
					.queryString("page", page)
					.asString()
					.getBody();
			// @formatter:on
		} catch (final UnirestException e) {
			throw new MolyHuException(e);
		}

		CitationsWrapper result;
		try {
			result = mapper.readValue(jsonResponse, CitationsWrapper.class);
		} catch (final IOException e) {
			throw new MolyHuException(e);
		}

		log.debug("result: {}", result);
		return result.getCitations();
	}

}
