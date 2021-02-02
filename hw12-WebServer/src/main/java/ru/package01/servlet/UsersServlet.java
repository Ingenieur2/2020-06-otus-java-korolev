package ru.package01.servlet;

import ru.package01.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;


public class UsersServlet extends HttpServlet {

    private static final String LOGIN_PAGE_TEMPLATE = "guestPage.html";
    private static final String USERS_PAGE_TEMPLATE = "users.html";

    private static final String TEMPLATE_ATTR_ADMIN = "admin";


    private final TemplateProcessor templateProcessor;

    public UsersServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        if (request.isUserInRole(TEMPLATE_ATTR_ADMIN)) {
            response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, Collections.emptyMap()));
        } else {

            response.getWriter().println(templateProcessor.getPage(LOGIN_PAGE_TEMPLATE, Collections.emptyMap()));
            response.setStatus(SC_UNAUTHORIZED);
            request.getSession().invalidate();
        }
    }
}

