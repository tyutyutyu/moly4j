package com.tyutyutyu.api.moly4j.book_by_isbn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@ToString
public class ResultByISBN {

	private int id;

	private String author;

	private String title;

	private String cover;

}
