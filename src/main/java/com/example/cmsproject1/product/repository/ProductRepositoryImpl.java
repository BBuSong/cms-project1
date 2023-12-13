package com.example.cmsproject1.product.repository;

import com.example.cmsproject1.product.entity.Product;
import com.example.cmsproject1.product.entity.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{

  private final JPAQueryFactory jpaQueryFactory;


  @Override
  public List<Product> searchByName(String productName) {

    String search = "%" + productName + "%";

    QProduct product = QProduct.product;

    return jpaQueryFactory.selectFrom(product)
        .where(product.productName.like(search))
        .fetch();

  }
}
