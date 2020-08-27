package com.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.entities.Products;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Products, Long> {

	 //public Slice<Products> findByName(Pageable pageable);
	
	@Query(value = "select p from Products p where p.name LIKE %?1%")
	 public List<Products>  findByName(//@Param("name")
			 String name);
	 //public List<Products>  findByNameContaining(String name);
	
}
