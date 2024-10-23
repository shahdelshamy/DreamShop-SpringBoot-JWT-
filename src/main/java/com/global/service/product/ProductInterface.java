package com.global.service.product;

import java.util.List;

import com.global.models.Product;
import com.global.requests.AddProduct;


public interface ProductInterface {

	
	public Product addProduct(AddProduct product);
	public Product getProductById(int id);
	public int deleteProductById(int id);
	public Product updateProduct(AddProduct product) throws Exception;
	
	public List<Product> getAllProducts();
	public List<Product> getProductByName(String name);
	public List<Product> getProductByCategory(String category);
	public List<Product> getProductByBrand(String brand);
	public List<Product> getProductByCategoryAndBrand(String category,String brand);
	public List<Product> getProductByNameAndBrand(String name,String brand);
	
	public int countAllProducts();
	public int coundProductByBrand(String brand);
	public int coundProductByCategory(String category);
	public int coundProductByNameAndBrand(String name,String brand);
	
	
	
	
}
