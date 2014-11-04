package com.tyutyutyu.molyhuapi.book_by_isbn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

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
