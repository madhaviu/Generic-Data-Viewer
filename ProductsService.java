package com.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.entities.Products;
import com.repo.ProductRepository;
import com.rest.ResponseEntity;

@Service
@Transactional
public class ProductsService {

	@Autowired
	ProductRepository productRepository;

	public ResponseEntity create(Products product) {
//		product.setId("P"+new Random().nextInt());
		Products p = productRepository.save(product);
	
		ResponseEntity response = new ResponseEntity();
		response.setData(p);
		
		return response;
		
	}
	 //public List<Products> getAllProducts(Integer pageNo, Integer pageSize, String sortBy)
	 public ResponseEntity getAllProducts(Integer pageNo, Integer pageSize, String sortBy)
	 {
	        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
	      //Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name").descending());
	 
	        Slice<Products> slicedResult = productRepository.findAll(paging); 
	         
	       // List<Products> prodlist = slicedResult.getContent();
	        
	        /*if(slicedResult.hasContent()) {
	            return slicedResult.getContent();
	        } else {
	            return new ArrayList<Products>();
	        }*/
	        ResponseEntity response = new ResponseEntity();
			response.setData(slicedResult.getContent());
			
			return response;
	  }
	
	 public ResponseEntity findByNameMatch(String nm) {

			List<Products> list = productRepository.findByName(nm);
			
			if (list != null) {
				ResponseEntity response = new ResponseEntity();
				response.setData(list);
				return response;
			}

			ResponseEntity response = new ResponseEntity();
			response.setErrors(true);
			response.setMessage("Product Not found");
			return response;
		}
}
