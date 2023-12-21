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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
public class ProductItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_no")
  private Long id;

  private Long userId;

  @Audited
  @Column(name = "product_name")
  private String productName;

  @Column(name = "size")
  private String size;

  @Audited
  @Column(name = "price")
  private int price;

  @Column(name = "delivery_fee")
  private int deliveryFee;

  @Enumerated(EnumType.STRING)
  private ProductStatus productStatus;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime modifiedAt;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_id")
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
