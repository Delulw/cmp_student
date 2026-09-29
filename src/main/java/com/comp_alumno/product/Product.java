package com.comp_alumno.product;

import java.math.BigDecimal;

public record Product(Long id, String name, String description, BigDecimal price) {
}