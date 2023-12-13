package com.example.cmsproject1.product.repository;

import com.example.cmsproject1.product.entity.Product;
import java.util.List;

public interface ProductRepositoryCustom {

  List<Product> searchByName(String ProductName);

}
