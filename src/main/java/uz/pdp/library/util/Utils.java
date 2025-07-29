package uz.pdp.library.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Utils {

    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("my-unit");
    }
}
