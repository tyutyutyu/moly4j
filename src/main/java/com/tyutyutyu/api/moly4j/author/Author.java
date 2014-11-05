package com.tyutyutyu.api.moly4j.author;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class Author {

	private int id;

	private String name;

	// TODO: rename
	private String full_name;

	private String url;

}
