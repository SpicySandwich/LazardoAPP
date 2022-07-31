package com.client.LazardoClient.Model;

import java.time.LocalDateTime;

import com.client.LazardoClient.Deserializer.LocalDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
	
	private Integer id;
	private String brand;
	private String name;
	private Double price;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, lenient = OptBoolean.FALSE)
   @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime currentdate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, lenient = OptBoolean.FALSE)
   @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime expirationdate;
	private String comment;

}