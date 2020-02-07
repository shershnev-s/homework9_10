package by.tut.shershnev_s.repository;

import by.tut.shershnev_s.repository.model.AllUserData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AllUserDataRepository {

    List<AllUserData> findAll(Connection connection) throws SQLException;

}
