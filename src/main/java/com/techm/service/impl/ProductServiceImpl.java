package com.techm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techm.entity.Biller;
import com.techm.entity.Product;
import com.techm.repository.ProductRepository;
import com.techm.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product addProducct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProductById(Long productId) {
		return productRepository.findById(productId).orElse(null);
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		List<Product> activeProducts = new ArrayList<>();
		for(Product product : allProducts) {
			if(product.isActive())
				activeProducts.add(product);
		}
		return activeProducts;
	}

	@Override
	public List<Product> getAllProductsByBiller(Biller biller) {
		return productRepository.findAllByBiller(biller);
	}

	@Override
	public void deleteProduct(Long productId) {
		Product product = getProductById(productId);
		product.setActive(false);
		productRepository.save(product);
		
	}

	@Override
	public void activeProduct(Long productId) {
		Product product = getProductById(productId);
		product.setActive(true);
		productRepository.save(product);
	}

	@Override
	public List<Product> getAllActiveProductsByBiller(Biller biller) {
		List<Product> allProduts = getAllProductsByBiller(biller);
		List<Product> products = new ArrayList<>();
		for(Product product : allProduts) {
			if(product.isActive())
				products.add(product);
		}
		return products;
	}

}
