package by.tut.shershnev_s.controller;

import by.tut.shershnev_s.service.UserInformationService;
import by.tut.shershnev_s.service.UserService;
import by.tut.shershnev_s.service.impl.UserInformationServiceImpl;
import by.tut.shershnev_s.service.impl.UserServiceImpl;
import by.tut.shershnev_s.service.model.AllUserDataDTO;
import by.tut.shershnev_s.service.model.UserInformationDTO;
import by.tut.shershnev_s.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAddServlet extends HttpServlet {

    private static final String PAGES = "/WEB-INF/pages/";
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private UserService userService = UserServiceImpl.getInstance();
    private UserInformationService userInformationService = UserInformationServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = PAGES + "add_user.jsp";
        RequestDispatcher view = getServletContext().getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> validateResult = validateData(req);
        if (!validateResult.isEmpty()) {
            req.setAttribute("errors", validateResult);
            doGet(req, resp);
        } else {
            AllUserDataDTO allUserDataDTO = addUser(req);
            String forward = PAGES + "print_added_user.jsp";
            RequestDispatcher view = getServletContext().getRequestDispatcher(forward);
            req.setAttribute("added_user", allUserDataDTO);
            view.forward(req, resp);
        }
    }

    private AllUserDataDTO addUser(HttpServletRequest req) {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String isActive = req.getParameter("isActive");
        String age = req.getParameter("age");
        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(name);
            userDTO.setPassword(password);
            boolean isActiveBool = Boolean.parseBoolean(isActive);
            userDTO.setActive(isActiveBool);
            int ageInt = Integer.parseInt(age);
            userDTO.setAge(ageInt);
            userDTO = userService.add(userDTO);
            UserInformationDTO userInformationDTO = new UserInformationDTO();
            userInformationDTO.setUserId(userDTO.getId());
            userInformationDTO.setAddress(address);
            userInformationDTO.setTelephone(telephone);
            userInformationService.add(userInformationDTO);
            return convertToAllUserDataDTO(userDTO, userInformationDTO);
        } catch (NumberFormatException e) {
            logger.error("Uncorrect format", e);
        }
        return null;
    }

    private AllUserDataDTO convertToAllUserDataDTO(UserDTO userDTO, UserInformationDTO userInformationDTO) {
        AllUserDataDTO allUserDataDTO = new AllUserDataDTO();
        allUserDataDTO.setId(userInformationDTO.getUserId());
        allUserDataDTO.setUsername(userDTO.getUsername());
        allUserDataDTO.setPassword(userDTO.getPassword());
        allUserDataDTO.setActive(userDTO.getIsActive());
        allUserDataDTO.setAge(userDTO.getAge());
        allUserDataDTO.setTelephone(userInformationDTO.getTelephone());
        allUserDataDTO.setAddress(userInformationDTO.getAddress());
        return allUserDataDTO;
    }

    private List<String> validateData(HttpServletRequest req) {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String isActive = req.getParameter("isActive");
        String age = req.getParameter("age");
        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        List<String> result = new ArrayList<>();
        result.addAll(validateName(name));
        result.addAll(validatePassword(password));
        result.addAll(validateIsActive(isActive));
        result.addAll(validateAge(age));
        result.addAll(validateAddress(address));
        result.addAll(validateTelephone(telephone));
        return result;
    }

    private List<String> validateName(String name) {
        List<String> result = new ArrayList<>();
        int maxLength = 15;
        if (name.length() > maxLength) {
            logger.error("User entered wrong format data in " + name);
            result.add("Use" + maxLength + "chars!");
        }
        Pattern pattern = Pattern.compile("[A-Za-zА]{1,15}");
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            logger.error("User entered wrong format data in " + name);
            result.add("Use only eng letters in " + name);
        }
        if (name.isEmpty()) {
            logger.error("User entered wrong format data in " + name);
            result.add("Empty field in " + name);
        }
        return result;
    }

    private List<String> validatePassword(String password) {
        List<String> result = new ArrayList<>();
        int maxLength = 9;
        if (password.length() > maxLength) {
            logger.error("User entered wrong format data in " + password);
            result.add("Use " + maxLength + " chars!");
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            logger.error("User entered wrong format data in " + password);
            result.add("Use only eng letters in " + password);
        }
        if (password.isEmpty()) {
            logger.error("User entered wrong format data in " + password);
            result.add("Empty field in " + password);
        }
        return result;
    }

    private List<String> validateIsActive(String isActive) {
        List<String> result = new ArrayList<>();
        int maxLength = 5;

        if (isActive.length() > maxLength) {
            logger.error("User entered wrong format data in " + isActive);
            result.add("Use " + maxLength + " chars!");
        }
        Pattern pattern = Pattern.compile("true|false");
        Matcher matcher = pattern.matcher(isActive);
        if (!matcher.matches()) {
            logger.error("User entered wrong format data in " + isActive);
            result.add("Use only true or false " + isActive);
        }
        if (isActive.isEmpty()) {
            logger.error("User entered wrong format data in " + isActive);
            result.add("Empty field in " + isActive);
        }
        return result;
    }

    private List<String> validateAge(String Age) {
        List<String> result = new ArrayList<>();
        int maxLength = 3;

        if (Age.length() > maxLength) {
            logger.error("User entered wrong format data in " + Age);
            result.add("Use " + maxLength + " chars!");
        }
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(Age);
        if (!matcher.matches()) {
            logger.error("User entered wrong format data in " + Age);
            result.add("Use only numbers " + Age);
        }
        if (Age.isEmpty()) {
            logger.error("User entered wrong format data in " + Age);
            result.add("Empty field in " + Age);
        }
        return result;
    }

    private List<String> validateAddress(String address) {
        List<String> result = new ArrayList<>();
        int maxLength = 30;

        if (address.length() > maxLength) {
            logger.error("User entered wrong format data in " + address);
            result.add("Use " + maxLength + " chars!");
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]+$");
        Matcher matcher = pattern.matcher(address);
        if (!matcher.matches()) {
            logger.error("User entered wrong format data in " + address);
            result.add("Only eng letters " + address);
        }
        if (address.isEmpty()) {
            logger.error("User entered wrong format data in " + address);
            result.add("Empty field in " + address);
        }
        return result;
    }

    private List<String> validateTelephone(String telephone) {
        List<String> result = new ArrayList<>();
        int maxLength = 18;
        if (telephone.length() > maxLength) {
            logger.error("User entered wrong format data in " + telephone);
            result.add("Use " + maxLength + " chars!");
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(telephone);
        if (!matcher.matches()) {
            logger.error("User entered wrong format data in " + telephone);
            result.add("Use less then 18 symbols " + telephone);
        }
        if (telephone.isEmpty()) {
            logger.error("User entered wrong format data in " + telephone);
            result.add("Empty field in " + telephone);
        }
        return result;
    }
}
