package com.example.WebBanHang.model;

import com.example.WebBanHang.validator.ValidUserId;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String  imageData;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ValidUserId
    private User user;

    private int quantity;
}