package com.client.LazardoClient.DAO;

import com.client.LazardoClient.Model.SellerChangesProduct;
import com.client.LazardoClient.Model.SellerProduct;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SellerDeleteUpdateProductRepo {
	
	final String DELETE_PRODUCT_SELLER = "DELETE FROM tblazardoproduct "
			+ "WHERE product_id = ( "
			+ "SELECT id FROM( "
			+ "SELECT lp.product_id AS id "
			+ "FROM tblazardoproduct lp "
			+ "LEFT JOIN tbclientdetails cd "
			+ "ON lp.sellerid = cd.client_id "
			+ "WHERE cd.client_email = #{sellerEmail} AND cd.role = 2 AND product_id = #{productId} "
			+ ") AS sellerdeleteproduct);";
	
	final String UPDATE_PRODUCT_SELLER = "UPDATE tblazardoproduct lp, tbclientdetails cd "
			+ "JOIN( "
			+ "SELECT lp2.sellerid AS sellid, cd2.client_email AS selleremail, cd2.role AS sellerrole, lp2.product_id AS productid "
			+ "FROM tbclientdetails cd2 "
			+ "LEFT JOIN tblazardoproduct lp2 "
			+ "ON cd2.client_id = lp2.sellerid "
			+ "WHERE cd2.client_email = #{clientSellerEmail} AND cd2.role = 2 AND lp2.product_id = #{id} "
			+ ") info "
			+ "SET lp.product_brand = #{brand}, lp.product_name = #{name},lp.product_price = #{price}, lp.product_comment = #{comment} "
			+ "WHERE lp.product_id = info.productid AND lp.sellerid = info.sellid AND cd.client_email = info.selleremail AND cd.role = info.sellerrole;";
	
	@Delete(DELETE_PRODUCT_SELLER)
	public boolean deleteSellerProduct(SellerChangesProduct changesProduct);
	
	@Update(UPDATE_PRODUCT_SELLER)
	public boolean updateSellerProduct(SellerProduct product);

}
