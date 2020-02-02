package by.tut.shershnev_s.service;

import by.tut.shershnev_s.service.model.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO add(UserDTO userDTO);

    List<UserDTO> findAll();

    void deleteByID(UserDTO userDTO);

}
