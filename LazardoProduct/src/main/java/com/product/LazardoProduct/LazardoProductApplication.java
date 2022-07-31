package com.product.LazardoProduct;

import com.product.LazardoProduct.Model.Product;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MappedTypes({Product.class})
@MapperScan("com.product.LazardoProduct.DAO")
public class LazardoProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(LazardoProductApplication.class, args);
	}

}
