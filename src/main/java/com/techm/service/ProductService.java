package com.techm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techm.entity.Biller;
import com.techm.entity.Product;

@Service
public interface ProductService {
	public Product addProducct(Product product);
	public Product updateProduct(Product product);
	public Product getProductById(Long productId);
	public List<Product> getAllProducts();
	public List<Product> getAllProductsByBiller(Biller biller);
	public void deleteProduct(Long productId);
	public void activeProduct(Long productId);
	public List<Product> getAllActiveProductsByBiller(Biller biller);
}
