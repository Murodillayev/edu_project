package uz.pdp.library.dao;

import uz.pdp.library.model.AuthUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AuthUserDao {

    private static AuthUserDao instance;


//    static {
//        AuthUserDao dao = getInstance();
//        AuthUser authUser = new AuthUser();
//        authUser.setUsername("admin");
//        authUser.setPassword("123");
//        authUser.setFullName("Muhammadkomil");
//        dao.create(authUser);
//
//    }
    public static AuthUserDao getInstance() {
        if (instance == null) {
            instance = new AuthUserDao();
        }
        return instance;
    }

    public Optional<AuthUser> findByUsername(String username) {
        // userId = 18; userId = 2121231


        // Statement  select, delete, update, insert

        // PrepareStatement  select, delete, update, insert 90 %

        // callbackstatement call procedures


        try (Connection connection = DatabaseConnector.getConnection();){

            String sql = "SELECT * FROM auth_user WHERE username = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                AuthUser authUser = new AuthUser();
                authUser.setId(resultSet.getString("id"));
                authUser.setUsername(resultSet.getString("username"));
                authUser.setFullName(resultSet.getString("full_name"));
                authUser.setPassword(resultSet.getString("password"));
                return Optional.of(authUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return Optional.empty();
    }
}

