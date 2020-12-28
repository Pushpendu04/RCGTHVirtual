package com.cognizant.identifyprofiles.exception;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class ExceptionResponseTest {

	@Test
	void testGetterSetterConstructors() {
		ExceptionResponse exceptionResponse1=new ExceptionResponse(LocalDateTime.of(2020,12,31,19,30,40), "Accepted", "false");
		ExceptionResponse exceptionResponse2=new ExceptionResponse();
		exceptionResponse2.setDate(LocalDateTime.of(2020,12,31,19,30,40));
		exceptionResponse2.setMessage("Accepted");
		exceptionResponse2.setDetails("false");
		
		assertEquals(LocalDateTime.of(2020,12,31,19,30,40),exceptionResponse2.getDate());
		assertEquals("Accepted",exceptionResponse2.getMessage());
		assertEquals("false",exceptionResponse1.getDetails());
	}


}
