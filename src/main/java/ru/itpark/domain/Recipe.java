package ru.itpark.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private String id;
    private String name;
    private String ingredients;
    private String description;
    }
