package br.com.glenio.montovani.core.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    public void update(Product newData) {
        this.name = newData.name;
        this.description = newData.description;
        this.price = newData.price;
        this.stock = newData.stock;
    }
}