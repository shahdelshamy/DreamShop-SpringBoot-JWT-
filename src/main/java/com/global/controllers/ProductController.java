package com.global.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Conflict;

import com.global.exceptions.ResourceNotFoundException;
import com.global.requests.AddProduct;
import com.global.response.ApiResponse;
import com.global.service.product.ProductService;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.CONFLICT;;

@RestController
@RequestMapping("${api.prefix}/product")
public class ProductController {

	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<ApiResponse>addProduct(@RequestBody AddProduct product){
		try {
			return ResponseEntity.ok(new ApiResponse("Sucess",productService.addProduct(product)));
		} catch (Exception e) {
			return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(),null));
		}
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<ApiResponse>updateProduct(@RequestBody AddProduct product){
		try {
			return ResponseEntity.ok(new ApiResponse("Sucess",productService.updateProduct(product)));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed",e.getMessage()));
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ApiResponse>getAll(){
			return ResponseEntity.ok(new ApiResponse("Sucess",productService.getAllProducts()));
	}
	
	@GetMapping("/productsByName")
	public ResponseEntity<ApiResponse>getProductByName(@PathVariable String name){
		try {
			return ResponseEntity.ok(new ApiResponse("Sucess",productService.getProductByName(name)));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed",e.getMessage()));
		}
	}
	
	@GetMapping("/productsByCategory")
	public ResponseEntity<ApiResponse>getProductByCategory(@PathVariable String name){
		try {
			return ResponseEntity.ok(new ApiResponse("Sucess",productService.getProductByCategory(name)));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed",e.getMessage()));
		}
	}
	
	@GetMapping("/productsByBrand")
	public ResponseEntity<ApiResponse>getProductByBrand(@PathVariable String name){
		try {
			return ResponseEntity.ok(new ApiResponse("Sucess",productService.getProductByBrand(name)));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed",e.getMessage()));
		}
	}
	
	@GetMapping("/productsByCategoryAndBrand")
	public ResponseEntity<ApiResponse>getProductByCategoryAndBrand(@RequestParam String category,@RequestParam String brand){
		try {
			return ResponseEntity.ok(new ApiResponse("Sucess",productService.getProductByCategoryAndBrand(category, brand)));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed",e.getMessage()));
		}
	}
	
	@GetMapping("/productsByNameAndBrand")
	public ResponseEntity<ApiResponse>getProductByNameAndBrand(@RequestParam String name,@RequestParam String brand){
		try {
			return ResponseEntity.ok(new ApiResponse("Sucess",productService.getProductByNameAndBrand(name, brand)));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed",e.getMessage()));
		}
	}
	
	@GetMapping("/productsCount")
	public ResponseEntity<ApiResponse>productsCount(){
		return ResponseEntity.ok(new ApiResponse("Sucess",productService.countAllProducts()));
	}
	
	@GetMapping("/productsCountByBrand")
	public ResponseEntity<ApiResponse>countProductByBrand(@PathVariable String brand){
		return ResponseEntity.ok(new ApiResponse("Sucess",productService.coundProductByBrand(brand)));
	}
	
	@GetMapping("/productsCountByCategory")
	public ResponseEntity<ApiResponse>countProductByCategory(@PathVariable String category){
		return ResponseEntity.ok(new ApiResponse("Sucess",productService.coundProductByBrand(category)));
	}
	
	@GetMapping("/productsCountByNameAndBrand")
	public ResponseEntity<ApiResponse>countProductByNameAndBrand(@RequestParam String name,@RequestParam String brand){
		return ResponseEntity.ok(new ApiResponse("Sucess",productService.coundProductByNameAndBrand(name, brand)));
	}
	
	
	
}
