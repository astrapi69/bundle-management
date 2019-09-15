package de.alpharogroup.bundlemanagement.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

@UtilityClass public class MvcResultAsserterExtensions
{

	public static <T> void assertEqual(ObjectMapper objectMapper, MvcResult mvcResult, T expected,
		Class<T> dtoClass, String... propertiesOrFieldsToIgnore) throws IOException
	{
		T dtoResult = getDtoResult(objectMapper, mvcResult, dtoClass);
		assertThat(dtoResult).isNotNull();
		assertThat(dtoResult).isEqualToIgnoringGivenFields(expected, propertiesOrFieldsToIgnore);
	}

	public static <T> T getDtoMvcResult(ObjectMapper objectMapper, MvcResult mvcResult, Class<T> dtoClass)
		throws IOException
	{
		T resultDto = getDtoResult(objectMapper, mvcResult, dtoClass);
		assertThat(resultDto).isNotNull();
		return resultDto;
	}

	private static <T> T getDtoResult(ObjectMapper objectMapper, MvcResult mvcResult, Class<T> dtoClass)
		throws IOException
	{
		assertThat(mvcResult.getResponse()).isNotNull();
		String resultString = mvcResult.getResponse().getContentAsString();
		return objectMapper.readValue(resultString, dtoClass);
	}

}
