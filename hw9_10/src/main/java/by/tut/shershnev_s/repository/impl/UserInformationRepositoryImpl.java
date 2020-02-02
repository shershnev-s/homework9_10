package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.UserInformationRepository;
import by.tut.shershnev_s.repository.model.UserInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserInformationRepositoryImpl implements UserInformationRepository {

    private static UserInformationRepository instance;

    public static UserInformationRepository getInstance() {
        if (instance == null) {
            instance = new UserInformationRepositoryImpl();
        }
        return instance;
    }

    @Override
    public UserInformation add(Connection connection, UserInformation userInformation) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO user_information(user_id, address, telephone) VALUES (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            statement.setLong(1, userInformation.getUserId());
            statement.setString(2, userInformation.getAddress());
            statement.setString(3, userInformation.getTelephone());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user information failed, no rows affected.");
            }
            return userInformation;
        }
    }

    @Override
    public List<UserInformation> findAll(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM user_information")
        ) {
            List<UserInformation> userInformation = new ArrayList<>();
            while (rs.next()) {
                UserInformation information = getUserInformation(rs);
                userInformation.add(information);
            }
            return userInformation;
        }
    }

    private UserInformation getUserInformation(ResultSet rs) throws SQLException {
        Long id = rs.getLong("user_id");
        String address = rs.getString("address");
        String telephone = rs.getString("telephone");
        UserInformation userInformation = new UserInformation();
        userInformation.setUserId(id);
        userInformation.setAddress(address);
        userInformation.setTelephone(telephone);
        return userInformation;
    }
}
