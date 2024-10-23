package com.global.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.exceptions.ResourceNotFoundException;
import com.global.models.Category;
import com.global.models.Product;
import com.global.repositories.CategoryRepository;
import com.global.repositories.ProductRepository;
import com.global.requests.AddProduct;


@Service
public class ProductService implements ProductInterface {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Product addProduct(AddProduct product) {

		Product saveProduct=new Product();
		
		Optional<Category> category=categoryRepository.findByName(product.getCategory().getName());
		if(category.isEmpty()) {
			Category category2=new Category(product.getCategory().getName());
			Category savedCategory=categoryRepository.save(category2);
			saveProduct.setCategory(savedCategory);
		}else {
			saveProduct.setCategory(category.get());
		}
		saveProduct.setBrand(product.getBrand());
		saveProduct.setDescription(product.getDescription());
		saveProduct.setName(product.getName());
		saveProduct.setPrice(product.getPrice());
		saveProduct.setQuantity(product.getQuantity());
		
		return productRepository.save(saveProduct);
	}

	@Override
	public Product updateProduct(AddProduct product)  {
		 Product checkProduct=productRepository.findById(product.getId()).get();
		 if(checkProduct != null) {
			 Optional<Category> category=categoryRepository.findByName(product.getCategory().getName());
				if(category.isEmpty()) {
					Category category2=new Category(product.getCategory().getName());
					Category savedCategory=categoryRepository.save(category2);
					checkProduct.setCategory(savedCategory);
				}else {
					checkProduct.setCategory(category.get());
				}
				checkProduct.setBrand(product.getBrand());
				checkProduct.setDescription(product.getDescription());
				checkProduct.setName(product.getName());
				checkProduct.setPrice(product.getPrice());
				checkProduct.setQuantity(product.getQuantity());
				return productRepository.save(checkProduct);
		 }else {
			 throw new ResourceNotFoundException("This product not found") ;
		 }
	}
	
	@Override
	public Product getProductById(int id) {
		return productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("product not found"));
	}
	
	@Override
	public int deleteProductById(int id) {
		return productRepository.deleteById(id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductByName(String name) {
		List<Product> products=productRepository.findByName(name);
		if(products.size()==0) {
			throw new ResourceNotFoundException("Not Found Products With This Name");
		}else {
			return products;
		}
		
		
	}

	@Override
	public List<Product> getProductByCategory(String category) {
		List<Product> products=productRepository.findByCategoryName(category);
		if(products.size()==0) {
			throw new ResourceNotFoundException("Not Found Products With This Category");
		}else {
			return products;
		}
	}

	@Override
	public List<Product> getProductByBrand(String brand) { 
		
			List<Product> products=productRepository.findByBrand(brand);
			if(products.size()==0) {
				throw new ResourceNotFoundException("Not Found Products With This Brand");
			}else {
				return products;
			}
	}

	@Override
	public List<Product> getProductByCategoryAndBrand(String category, String brand) {
		List<Product> products=productRepository.findByCategoryNameAndBrand(category,brand);
		if(products.size()==0) {
			throw new ResourceNotFoundException("Not Found Products With This Brand And Category");
		}else {
			return products;
		}
		
	}

	@Override
	public List<Product> getProductByNameAndBrand(String name, String brand) {
		List<Product> products=productRepository.findByNameAndBrand(name,brand);
		if(products.size()==0) {
			throw new ResourceNotFoundException("Not Found Products With This Brand And Name");
		}else {
			return products;
		}
	}

	@Override
	public int countAllProducts() {
		return (int) productRepository.count();
	}

	@Override
	public int coundProductByNameAndBrand(String name, String brand) {
		return productRepository.countByNameAndBrand(name,brand);
	}
	
	@Override
	public int coundProductByCategory(String category) {
		return productRepository.countByCategoryName(category);
	}
	
	@Override
	public int coundProductByBrand(String brand) {
		return productRepository.countByBrand(brand);
	}



}
