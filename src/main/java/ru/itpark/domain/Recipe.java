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
        return UUID.randomUUID().toString();
    }

}


//TODO
//
//        добавлен поиск по ингредиентам.
//        Нужно: усложнить его, сделав разбивку по запятой
//        Добавить поиск по наличию в холодильнике cookbookIlnaz
//
//        разнести поиск и ввод на 2 стр
//        Оставить на странице 4 рецепта, дальше offset на след стр
//        Добавить вид (через наследование?)
//        Сделать поиск по виду
