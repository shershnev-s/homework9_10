package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.AllUserDataRepository;
import by.tut.shershnev_s.repository.model.AllUserData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AllUserDataRepositoryImpl implements AllUserDataRepository {

    private static AllUserDataRepository instance;

    public static AllUserDataRepository getInstance() {
        if (instance == null) {
            instance = new AllUserDataRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<AllUserData> findAll(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT id,username,password,age,is_active, " +
                        "telephone, address FROM user INNER JOIN user_information WHERE id=user_id;")
        ) {
            List<AllUserData> allUserDataList = new ArrayList<>();
            while (rs.next()) {
                AllUserData allUserData = getAllUserData(rs);
                allUserDataList.add(allUserData);
            }
            return allUserDataList;
        }
    }

    private AllUserData getAllUserData(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        boolean isActive = rs.getBoolean("is_active");
        int age = rs.getInt("age");
        String address = rs.getString("address");
        String telephone = rs.getString("telephone");
        AllUserData allUserData = new AllUserData();
        allUserData.setId(id);
        allUserData.setUsername(username);
        allUserData.setPassword(password);
        allUserData.setActive(isActive);
        allUserData.setAge(age);
        allUserData.setAddress(address);
        allUserData.setTelephone(telephone);
        return allUserData;
    }
}
