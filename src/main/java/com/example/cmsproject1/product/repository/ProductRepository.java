package com.example.cmsproject1.product.repository;

import com.example.cmsproject1.product.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

  @EntityGraph(attributePaths = {"productItems"}, type = EntityGraphType.LOAD)
  Optional<Product> findByUserIdAndId(Long userId, Long id);

  @EntityGraph(attributePaths = {"productItems"}, type = EntityGraphType.LOAD)
  Optional<Product> findWithProductItemsById(Long id);

  @EntityGraph(attributePaths = {"productItems"}, type = EntityGraphType.LOAD)
  List<Product> findAllByIdIn(List<Long> ids);

}
