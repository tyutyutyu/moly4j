package com.tyutyutyu.api.moly4j.author;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@NoArgsConstructor
@ToString
public class Author {

	private int id;

	private String name;

	@JsonProperty("full_name")
	private String fullName;

	private String url;

}
