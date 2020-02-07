package by.tut.shershnev_s.controller;

import by.tut.shershnev_s.service.AllUserDataService;
import by.tut.shershnev_s.service.TableService;
import by.tut.shershnev_s.service.impl.AllUserDataServiceImpl;
import by.tut.shershnev_s.service.impl.TableServiceImpl;
import by.tut.shershnev_s.service.model.AllUserDataDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class PrintAllUsersServlet extends HttpServlet {

    private static final String PAGES = "/WEB-INF/pages/";
    private TableService tableService = TableServiceImpl.getInstance();
    private AllUserDataService allUserDataService = AllUserDataServiceImpl.getInstance();

    @Override
    public void init() throws ServletException {
        tableService.deleteAllTables();
        tableService.createAllTables();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AllUserDataDTO> userDataDTOS = allUserDataService.findAll();
        req.setAttribute("users", userDataDTOS);
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(PAGES + "users.jsp");
        requestDispatcher.forward(req, resp);
    }

}
