package by.tut.shershnev_s.repository;

import by.tut.shershnev_s.repository.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends GeneralRepository<User> {

    void deleteByID(Connection connection, Long id) throws SQLException;
}
