package server.servlets;

import server.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(name = "Main", urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getAttribute("user");
        req.setAttribute("name", user.getName());
        req.setAttribute("lastName", user.getLastName());


        Path root = Paths.get("/tmp").toAbsolutePath().normalize();
        Path p = Paths.get(user.getImagePath()).toAbsolutePath().normalize();
        Path rel = root.relativize(p);
        String url = "/images/uploads/" + rel.toString().replace(File.separatorChar, '/'); // затем процентизируйте сегменты
        req.setAttribute("imagePath", url);
        req.getRequestDispatcher("main.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
