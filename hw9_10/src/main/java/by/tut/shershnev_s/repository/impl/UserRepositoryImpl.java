package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.UserRepository;
import by.tut.shershnev_s.repository.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepository instance;

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void deleteByID(Connection connection, Long id) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM user WHERE id =?"
                )
        ) {
            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user information failed, no rows affected.");
            }
        }
    }

    @Override
    public User add(Connection connection, User user) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO user(username, password, is_active, age) VALUES (?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isActive());
            statement.setInt(4, user.getAge());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating item failed, no ID obtained.");
                }
            }
            return user;
        }
    }
}
