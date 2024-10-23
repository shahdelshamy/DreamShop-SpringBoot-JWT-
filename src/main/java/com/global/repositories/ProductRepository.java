package com.global.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("delete from Product p where p.id=:id")
	public int deleteById(int id);
	public List<Product> findByName(String name);
	public List<Product> findByCategory();
	public List<Product> findByNameAndBrand(String name,String brand);
	public List<Product> findByCategoryNameAndBrand(String category, String brand);
	public List<Product> findByBrand(String brand);
	public List<Product> findByCategoryName(String category);
	public int countByCategoryName(String category);
	public int countByNameAndBrand(String name, String brand);
	public int countByBrand(String brand);
	

}
