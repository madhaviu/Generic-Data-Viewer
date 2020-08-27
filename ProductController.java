package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Products;
import com.services.ProductsService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	ProductsService productsService;
	
	@GetMapping
    public ResponseEntity getAllProducts(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy) 
    {
		
        return productsService.getAllProducts(pageNo, pageSize, sortBy);
 
        //return new ResponseEntity(list, new HttpHeaders(), HttpStatus.OK); 
    }
	
	@PostMapping
	public ResponseEntity create(@RequestBody Products p ) 
	{
		return productsService.create(p);
	}
	
	/*@GetMapping("/byPriceLessthan/{price}")
	public ResponseEntity findByPriceLessThan(@PathVariable("price") Double price) {
		return productsService.findByPriceLessThan(price);
	}*/

	@GetMapping("/byNameLike/{name}")
	public ResponseEntity getByName(@PathVariable("name") String name) {
		return productsService.findByNameMatch(name);
	}

}
