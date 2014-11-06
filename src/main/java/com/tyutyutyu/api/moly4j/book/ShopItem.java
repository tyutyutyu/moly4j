package com.tyutyutyu.api.moly4j.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@NoArgsConstructor
@ToString
public class ShopItem {

	private String url;

	/**
	 * Eredeti ár
	 */
	@JsonProperty("original_price")
	private Integer originalPrice;

	/**
	 * (Kedvezményes) ár
	 */
	private Integer price;

	/**
	 * Kedvezmény
	 */
	private Double allowance;

}
