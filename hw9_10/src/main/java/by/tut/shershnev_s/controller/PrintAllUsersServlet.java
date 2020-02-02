package by.tut.shershnev_s.controller;

import by.tut.shershnev_s.service.TableService;
import by.tut.shershnev_s.service.UserInformationService;
import by.tut.shershnev_s.service.UserService;
import by.tut.shershnev_s.service.impl.TableServiceImpl;
import by.tut.shershnev_s.service.impl.UserInformationServiceImpl;
import by.tut.shershnev_s.service.impl.UserServiceImpl;
import by.tut.shershnev_s.service.model.AllUserDataDTO;
import by.tut.shershnev_s.service.model.UserInformationDTO;
import by.tut.shershnev_s.service.model.UserDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PrintAllUsersServlet extends HttpServlet {

    private static final String PAGES = "/WEB-INF/pages/";
    private TableService tableService = TableServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();
    private UserInformationService userInformationService = UserInformationServiceImpl.getInstance();

    @Override
    public void init() throws ServletException {
        tableService.deleteAllTables();
        tableService.createAllTables();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDTO> userDTOS = userService.findAll();
        List<UserInformationDTO> userInformationDTOS = userInformationService.findAll();
        List<AllUserDataDTO> userDataDTOS = convertToAllUserDataDTO(userDTOS, userInformationDTOS);
        req.setAttribute("users", userDataDTOS);
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(PAGES + "users.jsp");
        requestDispatcher.forward(req, resp);
    }

    private List<AllUserDataDTO> convertToAllUserDataDTO(List<UserDTO> userDTOS, List<UserInformationDTO> userInformationDTOS) {
        List<AllUserDataDTO> userDataDTOS = new ArrayList<>();
        for (int i = 0; i < userDTOS.size(); i++) {
            AllUserDataDTO allUserDataDTO = new AllUserDataDTO();
            UserDTO userDTO;
            userDTO = getUserDTO(i, userDTOS);
            allUserDataDTO.setUsername(userDTO.getUsername());
            allUserDataDTO.setPassword(userDTO.getPassword());
            allUserDataDTO.setActive(userDTO.getIsActive());
            allUserDataDTO.setAge(userDTO.getAge());
            UserInformationDTO userInformationDTO;
            userInformationDTO = getUserInformationDTO(i, userInformationDTOS);
            allUserDataDTO.setAddress(userInformationDTO.getAddress());
            allUserDataDTO.setTelephone(userInformationDTO.getTelephone());
            allUserDataDTO.setId(userDTO.getId());
            userDataDTOS.add(allUserDataDTO);
        }
        return userDataDTOS;
    }

    private UserDTO getUserDTO(int i, List<UserDTO> userDTOS) {
        return userDTOS.get(i);
    }

    private UserInformationDTO getUserInformationDTO(int i, List<UserInformationDTO> userInformationDTOS) {
        return userInformationDTOS.get(i);
    }
}
