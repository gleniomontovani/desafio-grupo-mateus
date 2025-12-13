package br.com.glenio.montovani.application.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String description,
        BigDecimal price,
        Integer stock
) {}
