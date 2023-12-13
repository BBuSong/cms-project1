package com.example.cmsproject1.product.entity;

import com.example.cmsproject1.domain.model.BaseEntity;
import com.example.cmsproject1.product.AddProductForm;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@AuditOverride(forClass = BaseEntity.class)
public class Product extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;

  private String productName;

  @ManyToMany(cascade = CascadeType.ALL)
  private List<ProductItem> productItems = new ArrayList<>();

  public static Product of(Long userId, AddProductForm form) {
    return Product.builder()
        .userId(userId)
        .productName(form.getProductName())
        .productItems(form.getItems().stream()
            .map(piFrom ->ProductItem.of(userId,piFrom)).collect(Collectors.toList()))
        .build();
  }
}
