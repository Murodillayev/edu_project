package uz.pdp.library.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import uz.pdp.library.model.AuthUser;
import uz.pdp.library.util.EntityManagerFactoryUtil;

import java.util.List;
import java.util.Optional;

public class AuthUserRepository {


    private static AuthUserRepository instance;

    public static AuthUserRepository getInstance() {
        if (instance == null) {
            instance = new AuthUserRepository();
        }
        return instance;
    }


    public void update(AuthUser authUser) {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryUtil.getMyUnitEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.merge(authUser); // put

        entityManager.getTransaction().commit();
    }

    public void create(AuthUser authUser) {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryUtil.getMyUnitEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(authUser); // patch

        entityManager.getTransaction().commit();
    }

    public Optional<AuthUser> findById(String id) {

        EntityManagerFactory entityManagerFactory = EntityManagerFactoryUtil.getMyUnitEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        AuthUser authUser = entityManager.find(AuthUser.class, id);
        entityManager.detach(authUser);
        entityManager.close();
        entityManagerFactory.close();
        return Optional.ofNullable(authUser);

    }

    public List<AuthUser> findAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<AuthUser> query = entityManager.createQuery("from AuthUser", AuthUser.class);
        List<AuthUser> authUsers = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();
        return authUsers;
    }

    public Optional<AuthUser> findByUsername(String username) {
        TestRepository.go();


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String jpql = "from AuthUser u where u.username = :username";
        TypedQuery<AuthUser> rs = entityManager.createQuery(jpql, AuthUser.class);
        rs.setParameter("username", username);
        AuthUser authUser = rs.getSingleResult();
        entityManager.close();
        entityManagerFactory.close();
        return Optional.ofNullable(authUser);

    }
}
