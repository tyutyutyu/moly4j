package com.tyutyutyu.molyhuapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class StringUtilsTest {

	@SuppressWarnings("static-method")
	@Test
	public void testStripAccents() {

		final String result = StringUtils.stripAccents("aáeéiíoóöőuúüű");
		assertThat(result).isEqualTo("aaeeiioooouuuu");
	}

}
