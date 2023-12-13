package com.example.cmsproject1.product.entity;

import com.example.cmsproject1.domain.model.BaseEntity;
import com.example.cmsproject1.product.AddProductItemForm;
import com.example.cmsproject1.product.ProductStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
public class ProductItem extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;

  @Audited
  private String productName;

  private String size;

  @Audited
  private Integer price;

  private Integer deliveryFee;


  @ManyToOne
  private Product product;

  public static ProductItem of(Long userId, AddProductItemForm form) {
    return ProductItem.builder()
        .userId(userId)
        .productName(form.getProductName())
        .price(form.getPrice())
        .deliveryFee(form.getDeliveryFee())
        .build();
  }

}
