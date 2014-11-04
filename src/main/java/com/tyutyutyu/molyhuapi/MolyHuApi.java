package com.tyutyutyu.molyhuapi;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.boon.HTTP;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

import com.tyutyutyu.molyhuapi.author.AuthorWrapper;
import com.tyutyutyu.molyhuapi.book.BookWrapper;
import com.tyutyutyu.molyhuapi.book.ResultById;
import com.tyutyutyu.molyhuapi.book_by_isbn.ResultByISBN;
import com.tyutyutyu.molyhuapi.book_citations.Citation;
import com.tyutyutyu.molyhuapi.book_citations.CitationsWrapper;
import com.tyutyutyu.molyhuapi.book_reviews.Review;
import com.tyutyutyu.molyhuapi.book_reviews.ReviewsWrapper;
import com.tyutyutyu.molyhuapi.books.BooksWrapper;
import com.tyutyutyu.molyhuapi.books.ResultByQuery;

@Slf4j
public class MolyHuApi {

	private static final MolyHuApi INSTANCE = new MolyHuApi();

	private static final String SEARCH_BY_QUERY_URL = "http://moly.hu/api/books.json?q={q}";
	private static final String GET_BY_ISBN_URL = "http://moly.hu/api/book_by_isbn.json?q={q}";
	private static final String GET_BY_ID_URL = "http://moly.hu/api/book/{id}.json";
	private static final String GET_AUTHOR_BY_ID_URL = "http://moly.hu/api/author/{id}.json";
	private static final String GET_REVIEWS_BY_ID_URL = "http://moly.hu/api/book_reviews/{id}.json?page={page}";
	private static final String GET_CITATIONS_BY_ID_URL = "http://moly.hu/api/book_citations/{id}.json?page={page}";

	private final ObjectMapper mapper = JsonFactory.create();

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
	 * @return
	 * @throws MolyHuException
	 */
	public List<ResultByQuery> searchByQuery(String q) throws MolyHuException {

		final String jsonResponse = HTTP.getJSON(SEARCH_BY_QUERY_URL.replace("{q}", StringUtils.stripAccents(q)), null);
		final BooksWrapper result = mapper.readValue(jsonResponse, BooksWrapper.class);

		log.debug("result: {}", result);
		return result.getBooks();
	}

	/**
	 * ISBN-kereső
	 *
	 * @param q
	 *            Keresőkifejezés
	 * @return
	 * @throws MolyHuException
	 */
	public ResultByISBN getByISBN(String q) throws MolyHuException {

		final String jsonResponse = HTTP.getJSON(GET_BY_ISBN_URL.replace("{q}", q), null);
		final ResultByISBN result = mapper.readValue(jsonResponse, ResultByISBN.class);

		log.debug("result: {}", result);
		return result;
	}

	/**
	 * Könyvadatok
	 *
	 * @param id
	 * @return
	 * @throws MolyHuException
	 */
	public ResultById getById(int id) throws MolyHuException {

		final String jsonResponse = HTTP.getJSON(GET_BY_ID_URL.replace("{id}", id + ""), null);
		final BookWrapper result = mapper.readValue(jsonResponse, BookWrapper.class);

		log.debug("result: {}", result);
		return result.getBook();
	}

	/**
	 * Szerzőadatok
	 *
	 * @param id
	 *            Szerző azonosító
	 * @return
	 * @throws MolyHuException
	 */
	public com.tyutyutyu.molyhuapi.author.Author getAuthorById(int id) throws MolyHuException {

		final String jsonResponse = HTTP.getJSON(GET_AUTHOR_BY_ID_URL.replace("{id}", id + ""), null);
		final AuthorWrapper result = mapper.readValue(jsonResponse, AuthorWrapper.class);

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
	 * @return
	 * @throws MolyHuException
	 */
	public List<Review> getBookReviews(int id, int page) throws MolyHuException {

		final String jsonResponse = HTTP.getJSON(
				GET_REVIEWS_BY_ID_URL.replace("{id}", id + "").replace("{page}", page + ""), null);
		final ReviewsWrapper result = mapper.readValue(jsonResponse, ReviewsWrapper.class);

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
	 * @return
	 * @throws MolyHuException
	 */
	public List<Citation> getBookCitations(String id, int page) throws MolyHuException {

		final String jsonResponse = HTTP.getJSON(
				GET_CITATIONS_BY_ID_URL.replace("{id}", id + "").replace("{page}", page + ""), null);
		final CitationsWrapper result = mapper.readValue(jsonResponse, CitationsWrapper.class);

		log.debug("result: {}", result);
		return result.getCitations();
	}

}
