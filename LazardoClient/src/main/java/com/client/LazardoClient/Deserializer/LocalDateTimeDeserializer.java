package com.client.LazardoClient.Deserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.client.LazardoClient.Validation.LocalDateTimeValidation;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import org.springframework.beans.factory.annotation.Autowired;

public class LocalDateTimeDeserializer  extends StdDeserializer<LocalDateTime>{
	
	private static LocalDateTimeValidation localDateTimeValidation;
	private static final long serialVersionUID = 1L;
	
	@Autowired
	public void autowairedFixStatic(LocalDateTimeValidation localDateTimeValidation) {
		LocalDateTimeDeserializer.localDateTimeValidation = localDateTimeValidation;
	}

	public LocalDateTimeDeserializer() {
		super(LocalDateTime.class);
	}

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        
		LocalDate localDate = LocalDate.parse(localDateTimeValidation.LocalDateTimeFormatValidation(p.getValueAsString()), localDateFormatter);
		
		return localDate.atStartOfDay();
	}
}
