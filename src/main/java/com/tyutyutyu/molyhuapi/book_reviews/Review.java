package com.tyutyutyu.molyhuapi.book_reviews;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Review {

	private int id;

	private int rating;

	private String review;

	private String url;

	private User user;

}
