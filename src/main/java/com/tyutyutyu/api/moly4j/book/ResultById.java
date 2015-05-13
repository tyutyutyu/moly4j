package com.tyutyutyu.api.moly4j.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tyutyutyu.api.moly4j.editor.Editor;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

@Getter
@NoArgsConstructor
@ToString(exclude = { "description" })
public class ResultById {

	private int id;

	private List<Author> authors;

	private List<Editor> editors;

	private String title;

	private String subtitle;

	private String cover;

	private String description;

	private String url;

	private List<Tag> tags;

	@JsonProperty("like_average")
	private double likeAverage;

	@JsonProperty("like_count")
	private int likeCount;

	@JsonProperty("shop_items")
	private List<ShopItem> shopItems;

	@AllArgsConstructor
	@Builder
	@Getter
	@NoArgsConstructor
	@Setter
	@ToString
	public static class Author {

		private int id;

		private String name;

	}

	@AllArgsConstructor
	@Builder
	@Getter
	@NoArgsConstructor
	@Setter
	@ToString
	public static class Tag {

		private int id;

		private String name;

	}

}
