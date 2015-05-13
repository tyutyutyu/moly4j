package com.tyutyutyu.api.moly4j.book_citations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString(exclude = { "citation" })
public class Citation {

	private int id;

	private String citation;

	private String url;

	private User user;

}
