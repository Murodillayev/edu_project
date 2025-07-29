package uz.pdp.library.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import uz.pdp.library.util.EntityManagerFactoryUtil;

import java.util.List;

public class TestRepository {





    public static void go(){
        EntityManagerFactory myUnitEntityManagerFactory = EntityManagerFactoryUtil.getMyUnitEntityManagerFactory();
        EntityManager myUnitEntityManager = myUnitEntityManagerFactory.createEntityManager();


        String sql = "from Test";

        Query query = myUnitEntityManager.createQuery(sql);
        List resultList = query.getResultList();
        for (Object object : resultList) {
            System.out.println(object);
        }

    }
}
