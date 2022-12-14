package com.product.LazardoProduct.DAO;

import java.util.List;

import com.product.LazardoProduct.Model.Product;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductRepo {
	
	
	@Select("SELECT "
			+ "product_id as id, "
			+ "product_brand as brand, "
			+ "product_name as name, "
			+ "product_price as price, "
			+ "product_current_date as currentdate, "
			+ "product_expiration_date as expirationdate, "
			+ "product_comment as comment "
			+ "FROM tblazardoproduct")
	public List<Product> productList();
	
	@Select("SELECT "
			+ "product_id as id, "
			+ "product_brand as brand, "
			+ "product_name as name, "
			+ "product_price as price, "
			+ "product_current_date as currentdate, "
			+ "product_expiration_date as expirationdate, "
			+ "product_comment as comment "
			+ "FROM tblazardoproduct "
			+ "WHERE product_id = #{id}")

	public Product findProduct(Integer id);

	
	@Update("UPDATE tblazardoproduct SET "
			+ "product_brand = #{brand}, "
			+ "product_name = #{name}, "
			+ "product_price = #{price}, "
			+ "product_current_date = #{currentdate}, "
			+ "product_expiration_date = #{expirationdate}, "
			+ "product_comment = #{comment}"
			+ "WHERE product_id = #{id}")
	void updateProduct(Product product);
	
	
	@Insert("INSERT INTO tblazardoproduct ("
			+ "product_brand,"
			+ "product_name,"
			+ "product_price,"
			+ "product_current_date,"
			+ "product_expiration_date,"
			+ "product_comment )"
			+ "VALUES ("
			+ "#{brand}, "
			+ "#{name}, "
			+ "#{price}, "
			+ "#{currentdate}, "
			+ "#{expirationdate}, "
			+ "#{comment})")
	
	public int addProduct(Product product);
	
	
	@Delete("DELETE FROM tblazardoproduct WHERE product_id = #{id}")
	public int deleteProduct(Integer id);
	
	 @Select("SELECT EXISTS(SELECT 1 FROM tblazardoproduct WHERE product_id = #{id})")
	 boolean ifIDExist(Integer id);


}
