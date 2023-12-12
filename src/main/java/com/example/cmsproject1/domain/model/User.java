package com.example.cmsproject1.domain.model;

import com.example.cmsproject1.domain.SignUpForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class User extends BaseEntity{

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String userId;
  private String name;
  private String password;
  private String cardNumber;

  public static User from(SignUpForm form) {
    return User.builder()
              .userId(form.getUserId())
              .name(form.getName())
              .password(form.getPassword())
              .cardNumber(form.getCardNumber())
              .build();
  }

}
