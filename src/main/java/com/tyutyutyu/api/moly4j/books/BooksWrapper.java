package com.tyutyutyu.api.moly4j.books;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class BooksWrapper {

	private List<ResultByQuery> books;

}
