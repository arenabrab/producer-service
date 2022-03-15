package com.example.producerservice.records;

import java.math.BigDecimal;

public record Animal(String name, int eyes, int legs, String type, boolean tail, BigDecimal price, boolean hasHair) {
}
