package uz.pdp.library.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getMyUnitEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("my-unit");
        }
        return emf;
    }

    public static void closeMyUnitEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }
        emf = null;
    }
}
