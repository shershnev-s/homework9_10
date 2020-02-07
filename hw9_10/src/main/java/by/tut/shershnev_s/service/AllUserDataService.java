package by.tut.shershnev_s.service;

import by.tut.shershnev_s.service.model.AllUserDataDTO;

import java.util.List;

public interface AllUserDataService {

    List<AllUserDataDTO> findAll();

}
