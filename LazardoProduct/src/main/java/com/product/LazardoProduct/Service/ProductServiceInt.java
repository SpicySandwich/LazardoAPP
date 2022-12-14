package com.product.LazardoProduct.Service;

import java.util.List;

import com.product.LazardoProduct.DTO.ProductDTO;
import com.product.LazardoProduct.Model.Product;

public interface ProductServiceInt {
	
	public ProductDTO saveProductbyDTO(Product product);
	
	public ProductDTO findProductbyDTO(Integer id);
	
	public List<ProductDTO> listProductbyDTO();
	
	public ProductDTO updateProduct(Product product);
	
	public Integer deleteProduct(Integer id);

}
