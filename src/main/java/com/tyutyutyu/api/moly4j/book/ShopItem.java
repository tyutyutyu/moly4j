package com.tyutyutyu.api.moly4j.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ShopItem {

	private String url;

	/**
	 * Eredeti ár
	 */
	// TODO: rename
	private Integer original_price;

	/**
	 * (Kedvezményes) ár
	 */
	private Integer price;

	/**
	 * Kedvezmény
	 */
	private Double allowance;

}
