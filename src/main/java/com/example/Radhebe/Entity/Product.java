package com.example.Radhebe.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "gross_weight", nullable = false, precision = 10, scale = 3)
    private BigDecimal grossWeight;

    @Column(name = "net_weight", nullable = false, precision = 10, scale = 3)
    private BigDecimal netWeight;

    @Column(name = "stone_weight", nullable = false, precision = 10, scale = 3)
    private BigDecimal stoneWeight = BigDecimal.ZERO;

    @Column(name = "s1_weight", nullable = false, precision = 10, scale = 3)
    private BigDecimal s1Weight = BigDecimal.ZERO;

    @Column(name = "s2_weight", nullable = false, precision = 10, scale = 3)
    private BigDecimal s2Weight = BigDecimal.ZERO;

    @Column(name = "carats", nullable = false)
    private Integer carats;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @Column(name = "barcode_number", unique = true)
    private String barcodeNumber;

    @Column(name = "in_stock", nullable = false)
    private Boolean inStock = true;

    @PrePersist
    @PreUpdate
    private void calculateNetWeight() {
        if (grossWeight != null && stoneWeight != null && s1Weight != null && s2Weight != null) {
            this.netWeight = grossWeight.subtract(stoneWeight).subtract(s1Weight).subtract(s2Weight);
        }
    }
}
