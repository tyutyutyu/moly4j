package com.tyutyutyu.molyhuapi.book_reviews;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ReviewsWrapper {

	private List<Review> reviews;

}
