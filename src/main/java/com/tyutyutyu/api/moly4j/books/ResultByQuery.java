package com.tyutyutyu.api.moly4j.books;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ResultByQuery {

	private long id;

	private String author;

	private String title;

}
