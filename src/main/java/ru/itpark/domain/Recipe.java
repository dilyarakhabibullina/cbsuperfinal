package ru.itpark.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    public String id = generateId();
    private String name;
    private String ingredients;
    private String description;

    public String generateId() {
        return UUID.randomUUID ( ).toString ( );
    }
}
