package com.comp_alumno.product;

import java.math.BigDecimal;

public record ProductRequest(String name, String description, BigDecimal price) {
}