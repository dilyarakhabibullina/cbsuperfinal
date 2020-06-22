package ru.itpark;

import ru.itpark.service.CookService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CookService service = new CookService ();
        System.out.println (service.getAll ());
        System.out.println (service.searchByName ("����") );
        System.out.println (service.getById ("7586d30d-6181-4500-80b8-2ea89a8850a2"));
        System.out.println(service.searchByIngredients("horosho"));
    }
}
