package by.tut.shershnev_s.service;

import by.tut.shershnev_s.service.model.UserDTO;

public interface UserService {

    UserDTO add(UserDTO userDTO);

    void deleteByID(UserDTO userDTO);

}
