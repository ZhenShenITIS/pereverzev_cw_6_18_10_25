package server.services.impl;

import server.dao.UserDao;
import server.dto.UserRegistrationDto;
import server.entities.User;
import server.services.SignUpService;
import server.util.PasswordUtil;


public class SignUpServiceImpl implements SignUpService {
    private UserDao userDao;

    @Override
    public boolean signUp(UserRegistrationDto userRegistrationDto) {
        if (userDao.getByLogin(userRegistrationDto.getLogin()) != null){
            return false;
        }
        String name = userRegistrationDto.getName();
        String lastName = userRegistrationDto.getLastName();
        String login = userRegistrationDto.getLogin();
        String password = PasswordUtil.encrypt(userRegistrationDto.getPassword());
        User user = new User(name, lastName, login, password);
        userDao.save(user);
        return true;

    }

    public SignUpServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
}
