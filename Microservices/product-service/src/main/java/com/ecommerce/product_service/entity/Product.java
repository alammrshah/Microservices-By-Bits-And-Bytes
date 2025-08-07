package com.ecommerce.product_service.entity;

import com.ecommerce.product_service.config.IdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String productId;

    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private Boolean inStock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    public void updateStockStatus() {
        this.inStock = this.stockQuantity != null && this.stockQuantity>0;
        if(createdAt == null) createdAt = LocalDateTime.now();
        updatedAt= LocalDateTime.now();

            if (this.productId == null) {
                this.productId = "prod-" + String.format("%05d", IdGenerator.getNextCategoryId());
            }
    }
}
