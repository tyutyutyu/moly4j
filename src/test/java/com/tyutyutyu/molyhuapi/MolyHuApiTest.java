package com.tyutyutyu.molyhuapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.tyutyutyu.api.moly4j.MolyHuApi;
import com.tyutyutyu.api.moly4j.MolyHuException;
import com.tyutyutyu.api.moly4j.book.ResultById;
import com.tyutyutyu.api.moly4j.book_by_isbn.ResultByISBN;
import com.tyutyutyu.api.moly4j.book_citations.Citation;
import com.tyutyutyu.api.moly4j.book_reviews.Review;
import com.tyutyutyu.api.moly4j.books.ResultByQuery;

public class MolyHuApiTest {

	@SuppressWarnings("static-method")
	@Test
	public void testSearchByQuery() throws MolyHuException, InterruptedException {

		Thread.sleep(1000);

		final List<ResultByQuery> results = MolyHuApi.getInstance().searchByQuery("vándorünnep");
		assertThat(results).isNotNull();
		assertThat(results).hasSize(3);
		assertThat(results.get(0).getId()).isEqualTo(15331);
		assertThat(results.get(0).getAuthor()).isEqualTo("Ernest Hemingway");
		assertThat(results.get(0).getTitle()).isEqualTo("V\u00e1ndor\u00fcnnep");
	}

	@SuppressWarnings("static-method")
	@Test
	public void testSearchByISBN() throws MolyHuException, InterruptedException {

		Thread.sleep(1000);

		final ResultByISBN result = MolyHuApi.getInstance().getByISBN("963825419X");
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(15331);
		assertThat(result.getAuthor()).isEqualTo("Ernest Hemingway");
		assertThat(result.getTitle()).isEqualTo("V\u00e1ndor\u00fcnnep");
		assertThat(result.getCover()).isEqualTo("http://moly.hu/system/covers/normal/covers_49254.jpg");
	}

	@SuppressWarnings("static-method")
	@Test
	public void testSearchById() throws MolyHuException, InterruptedException {

		Thread.sleep(1000);

		final ResultById result = MolyHuApi.getInstance().getById(15331);
		assertThat(result).isNotNull();
		assertThat(result.getAuthors()).hasSize(1);
		assertThat(result.getTitle()).isEqualTo("V\u00e1ndor\u00fcnnep");
		assertThat(result.getCover()).isEqualTo("http://moly.hu/system/covers/normal/covers_31532.jpg");
		assertThat(result.getUrl()).isEqualTo("http://moly.hu/konyvek/ernest-hemingway-vandorunnep");
		assertThat(result.getTags()).hasSize(4);
		assertThat(result.getLike_average()).isBetween(1D, 5D);
		assertThat(result.getLike_count()).isBetween(0, Integer.MAX_VALUE);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testSearchByIdTestShopItems() throws MolyHuException, InterruptedException {

		Thread.sleep(1000);

		final ResultById result = MolyHuApi.getInstance().getById(8771);

		assertThat(result).isNotNull();
		assertThat(result.getShop_items()).hasSize(1);
		assertThat(result.getShop_items().get(0).getOriginal_price()).isBetween(4000, 6000);
		assertThat(result.getShop_items().get(0).getPrice()).isBetween(2000, 6000);
		assertThat(result.getShop_items().get(0).getAllowance()).isBetween(0D, 1D);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testGetBookReviews() throws MolyHuException, InterruptedException {

		Thread.sleep(1000);

		final List<Review> results = MolyHuApi.getInstance().getBookReviews(15331, 1);

		assertThat(results).isNotNull();
		assertThat(results).hasSize(10);
		assertThat(results.get(0).getId()).isEqualTo(1104475);
		assertThat(results.get(0).getRating()).isEqualTo(10);
		assertThat(results.get(0).getReview()).isNotEmpty();
		assertThat(results.get(0).getUrl()).isEqualTo("http://moly.hu/ertekelesek/1104475");
		assertThat(results.get(0).getUser()).isNotNull();
		assertThat(results.get(0).getUser().getId()).isEqualTo(10212);
		assertThat(results.get(0).getUser().getLogin()).isEqualTo("Tilla");
	}

	@SuppressWarnings("static-method")
	@Test
	public void testGetBookCitations() throws MolyHuException, InterruptedException {

		Thread.sleep(1000);

		final List<Citation> results = MolyHuApi.getInstance().getBookCitations("15331", 1);

		assertThat(results).isNotNull();
		assertThat(results).hasSize(10);
		assertThat(results.get(0).getId()).isEqualTo(172615);
		assertThat(results.get(0).getCitation()).isNotEmpty();
		assertThat(results.get(0).getUrl()).isEqualTo("http://moly.hu/idezetek/172615");
		assertThat(results.get(0).getUser()).isNotNull();
		assertThat(results.get(0).getUser().getId()).isEqualTo(12902);
		assertThat(results.get(0).getUser().getLogin()).isEqualTo("Mormi");
	}

}
