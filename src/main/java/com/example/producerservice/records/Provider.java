package com.example.producerservice.records;

public record Provider(String name, int age, Pet pet) { // the names of these parameters must be exactly what consumer expects
}
