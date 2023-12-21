package com.example.cmsproject1.product.repository;

import com.example.cmsproject1.product.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

}
