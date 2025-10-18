package server.servlets;

import server.dto.UserRegistrationDto;
import server.services.SignUpService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet(name = "SignUp", urlPatterns = {"/signup", "/signUp"})
public class SignUpServlet extends HttpServlet {
    SignUpService signUpService;

    public static final String FILE_PREFIX = "/tmp";
    public static final int DIRECTORIES_COUNT = 100;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("sign_up.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (signUpService.signUp(new UserRegistrationDto(name, lastName, login, password))) {
            resp.sendRedirect("success_registration.ftl");
        } else {
            resp.sendRedirect("already_signed_up.ftl");
        }


        Part part = req.getPart("file");
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

        File file = new File(FILE_PREFIX + File.separator
                + Math.abs(filename.hashCode()) % DIRECTORIES_COUNT + File.separator + filename);
        InputStream content = part.getInputStream();
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[content.available()];
        content.read(buffer);
        fis. write(buffer);
        fis.close();
    }

    @Override
    public void init() throws ServletException {
        signUpService = (SignUpService) getServletContext().getAttribute("signUpService");
    }
}
