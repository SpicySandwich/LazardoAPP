package com.product.LazardoProduct.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.product.LazardoProduct.ConvertModelToDTO.ModelToDTObuilder;
import com.product.LazardoProduct.DAO.ProductRepo;
import com.product.LazardoProduct.DTO.ProductDTO;
import com.product.LazardoProduct.Model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements ProductServiceInt{
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ModelToDTObuilder convertToDTO;

	@Override
	public ProductDTO saveProductbyDTO(Product product) {
		productRepo.addProduct(product);
		return convertToDTO.producttoDTOsave(product);
	
	}

	@Override
	public ProductDTO findProductbyDTO(Integer id) {
		return convertToDTO.producttoDTO(productRepo.findProduct(id)); 
	}

	@Override
	public List<ProductDTO> listProductbyDTO() {
		return productRepo.productList().stream().map(convertToDTO :: producttoDTO).collect(Collectors.toList());
	
	}

	@Override
	public ProductDTO updateProduct(Product product) {
		productRepo.updateProduct(product);
	return convertToDTO.producttoDTO(product);
	}

	@Override
	public Integer deleteProduct(Integer id) {

		ProductDTO productDTO = ProductDTO.builder().id(productRepo.deleteProduct(id)).build();
		
		return productDTO.getId();
	}

}
