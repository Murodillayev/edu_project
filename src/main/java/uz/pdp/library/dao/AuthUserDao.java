package uz.pdp.library.dao;

import uz.pdp.library.model.AuthUser;
import uz.pdp.library.model.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthUserDao {

    private static final List<AuthUser> authUsers = new ArrayList<>();

    static {
        AuthUser authUser = new AuthUser();
        authUser.setUsername("admin");
        authUser.setFullName("Muhammadkomil");
        authUser.setPassword("123");
        authUsers.add(authUser);
    }

    private static AuthUserDao instance;

    public static AuthUserDao getInstance() {
        if (instance == null) {
            instance = new AuthUserDao();
        }
        return instance;
    }


    public Optional<AuthUser> findByUsername(String username) {
        return authUsers
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }
}

