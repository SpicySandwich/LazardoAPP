package com.product.LazardoProduct.Controller;

import java.util.List;

import com.product.LazardoProduct.DTO.ProductDTO;
import com.product.LazardoProduct.Model.Product;
import com.product.LazardoProduct.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/find/{id}")
	public ProductDTO findProductCont(@PathVariable Integer id) {
		
	return productService.findProductbyDTO(id);
	}
	
	@GetMapping("/list")
    public List<ProductDTO> listProduct(){
		return productService.listProductbyDTO();
	}
	
	@PostMapping("/add")
	public ProductDTO addProductCont(@RequestBody Product product) {
		return productService.saveProductbyDTO(product);
	}
	
	@DeleteMapping("/delete/{id}")
	public Integer deleteProduct(@PathVariable Integer id) {
		return productService.deleteProduct(id);
	}
	
	@PutMapping("/update")
	public ProductDTO updateProduct(@RequestBody Product product) {
		
		return productService.updateProduct(product);
	}

}
