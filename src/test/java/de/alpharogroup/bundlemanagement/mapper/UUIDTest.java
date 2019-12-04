package de.alpharogroup.bundlemanagement.mapper;

import org.junit.Test;

import java.util.UUID;

public class UUIDTest
{
	@Test
	public void testGenerateUUID(){
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);
	}
}
