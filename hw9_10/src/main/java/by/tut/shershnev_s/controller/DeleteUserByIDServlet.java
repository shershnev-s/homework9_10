package by.tut.shershnev_s.controller;

import by.tut.shershnev_s.service.UserService;
import by.tut.shershnev_s.service.impl.UserServiceImpl;
import by.tut.shershnev_s.service.model.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserByIDServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        UserDTO userDTO = new UserDTO();
        Long id = Long.parseLong(userId);
        userDTO.setId(id);
        userService.deleteByID(userDTO);
        String path = getServletContext().getContextPath();
        resp.sendRedirect(path + "/print_users");
    }
}
