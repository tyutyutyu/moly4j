package com.tyutyutyu.api.moly4j.book_citations;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class CitationsWrapper {

	private List<Citation> citations;

}
