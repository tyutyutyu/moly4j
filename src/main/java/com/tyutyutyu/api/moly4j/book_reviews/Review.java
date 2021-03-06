package com.tyutyutyu.api.moly4j.book_reviews;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString(exclude = { "review" })
public class Review {

	private int id;

	private int rating;

	private String review;

	private String url;

	private User user;

}
