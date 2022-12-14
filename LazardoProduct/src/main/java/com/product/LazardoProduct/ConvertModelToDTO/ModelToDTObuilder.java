package com.product.LazardoProduct.ConvertModelToDTO;

import java.util.Arrays;
import java.util.List;

import com.product.LazardoProduct.DTO.ProductDTO;
import com.product.LazardoProduct.Model.Product;

import org.springframework.stereotype.Service;

@Service
public class ModelToDTObuilder {
	
	public ProductDTO producttoDTO(Product product) {
		
	ProductDTO productDTO =	ProductDTO.builder()
		.id(product.getId())
		.brand(product.getBrand())
		.name(product.getName())
		.price(product.getPrice())
		.currentdate(product.getCurrentdate())
		.expirationdate(product.getExpirationdate())
		.comment(product.getComment())
		.build();
	
	return productDTO;
	}
	
	public ProductDTO producttoDTOsave(Product product) {
		
		ProductDTO productDTO =	ProductDTO.builder()
			.brand(product.getBrand())
			.name(product.getName())
			.price(product.getPrice())
			.currentdate(product.getCurrentdate())
			.expirationdate(product.getExpirationdate())
			.comment(product.getComment())
			.build();
		
		return productDTO;
		}
		
	
	
	public ProductDTO productListtoDTO() {
		
		Product product = new Product();
		
		ProductDTO productDTO =	ProductDTO.builder()
				.id(product.getId())
				.brand(product.getBrand())
				.name(product.getName())
				.price(product.getPrice())
				.currentdate(product.getCurrentdate())
				.expirationdate(product.getExpirationdate())
				.comment(product.getComment())
				.build();
			
			return productDTO;
	}

}
