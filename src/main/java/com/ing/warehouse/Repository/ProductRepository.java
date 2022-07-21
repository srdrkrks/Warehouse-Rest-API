package com.ing.warehouse.Repository;

import com.ing.warehouse.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product  p where p.isDeleted=false order by p.name asc")
    List<Product> findAllProducts();

    @Query("SELECT p from Product  p where  p.name=:name and p.isDeleted=false")
    Optional<Product> findByProductName(@Param("name") String name);

    @Query("SELECT p from Product  p where p.isDeleted=false and p.id=:id")
    Optional<Product> findByProductId(@Param("id") Long id);


    @Query("SELECT p from Product  p where p.isDeleted=false and p.name like %:searchVal%   order by  p.name asc")
    List<Product> searchProductName(@Param("searchVal") String name);
}
