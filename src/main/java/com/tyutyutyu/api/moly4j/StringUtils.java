package com.tyutyutyu.api.moly4j;

import java.text.Normalizer;

public final class StringUtils {

	private StringUtils() {
	}

	public static String stripAccents(String s) {

		return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	}

}
