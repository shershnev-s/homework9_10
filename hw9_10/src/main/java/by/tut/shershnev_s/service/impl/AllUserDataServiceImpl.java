package by.tut.shershnev_s.service.impl;

import by.tut.shershnev_s.repository.AllUserDataRepository;
import by.tut.shershnev_s.repository.ConnectionRepository;
import by.tut.shershnev_s.repository.impl.AllUserDataRepositoryImpl;
import by.tut.shershnev_s.repository.impl.ConnectionRepositoryImpl;
import by.tut.shershnev_s.repository.model.AllUserData;
import by.tut.shershnev_s.service.AllUserDataService;
import by.tut.shershnev_s.service.model.AllUserDataDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllUserDataServiceImpl implements AllUserDataService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static AllUserDataService instance;
    private ConnectionRepository connectionRepository = ConnectionRepositoryImpl.getInstance();
    private AllUserDataRepository allUserDataRepository = AllUserDataRepositoryImpl.getInstance();

    public static AllUserDataService getInstance() {
        if (instance == null) {
            instance = new AllUserDataServiceImpl();
        }
        return instance;
    }

    @Override
    public List<AllUserDataDTO> findAll() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            List<AllUserDataDTO> allUserDataDTOS = new ArrayList<>();
            try {
                List<AllUserData> people = allUserDataRepository.findAll(connection);
                connection.commit();
                AllUserDataDTO allUserDataDTO;
                for (AllUserData allUserData : people) {
                    allUserDataDTO = convertUserToAllUserDataDTO(allUserData);
                    allUserDataDTOS.add(allUserDataDTO);
                }
                return allUserDataDTOS;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                logger.error("Can't find user");
            }
        } catch (
                SQLException e) {
            logger.error(e.getMessage(), e);
            logger.error("Can't create connection");
        }
        return Collections.emptyList();
    }

    private AllUserDataDTO convertUserToAllUserDataDTO(AllUserData allUserData) {
        AllUserDataDTO allUserDataDTO = new AllUserDataDTO();
        allUserDataDTO.setId(allUserData.getId());
        allUserDataDTO.setUsername(allUserData.getUsername());
        allUserDataDTO.setPassword(allUserData.getPassword());
        allUserDataDTO.setActive(allUserData.isActive());
        allUserDataDTO.setAge(allUserData.getAge());
        allUserDataDTO.setTelephone(allUserData.getTelephone());
        allUserDataDTO.setAddress(allUserData.getAddress());
        return allUserDataDTO;
    }
}
