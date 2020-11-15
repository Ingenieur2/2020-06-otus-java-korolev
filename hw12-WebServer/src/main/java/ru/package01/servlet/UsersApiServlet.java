package ru.package01.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import ru.package01.core.model.User;
import ru.package01.core.service.DbServiceException;
import ru.package01.core.service.DbServiceUser;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersApiServlet extends HttpServlet {

    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_AGE = "age";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_NAME = "name";

    private final DbServiceUser userService;

    public UsersApiServlet(DbServiceUser userService) {
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter(PARAM_LOGIN);
        String age = request.getParameter(PARAM_AGE);
        String password = request.getParameter(PARAM_PASSWORD);
        String name = request.getParameter(PARAM_NAME);

        try {
            User newUser = new User(name);
            newUser.setLogin(login);
            newUser.setAge(Integer.parseInt(age));
            newUser.setPassword(password);

            userService.saveUser(newUser);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (DbServiceException e) {

            response.setContentType("text/html;charset=UTF-8");
            response.getOutputStream().print(e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String data = usersToJsonArray().toString();

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        out.print(data);
    }

    private JsonObject userToJsonObject(User user) {
        JsonObject userObject = new JsonObject();
        if (user != null) {
            userObject.add("id", new JsonPrimitive(user.getId()));
            userObject.add("name", new JsonPrimitive(user.getName()));
            userObject.add("age", new JsonPrimitive(user.getAge()));
            userObject.add("login", new JsonPrimitive(user.getLogin()));
            userObject.add("password", new JsonPrimitive(user.getPassword()));
        }
        return userObject;
    }

    private JsonArray usersToJsonArray() {
        JsonArray jsonArray = new JsonArray();
        userService.getAll().forEach(user -> {
            jsonArray.add(userToJsonObject(user));
        });
        return jsonArray;
    }
}
