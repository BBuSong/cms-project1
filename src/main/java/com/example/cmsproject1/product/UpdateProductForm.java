package com.example.cmsproject1.product;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductForm {

  private Long id;
  private String productName;
  private List<UpdateProductItemForm> items;

}
