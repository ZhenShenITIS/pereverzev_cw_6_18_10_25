package server.services.impl;



import server.dao.UserDao;
import server.dto.UserDto;
import server.entities.User;
import server.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(u-> new UserDto(u.getName(), u.getLastName(), u.getLogin(), u.getImagePath())).toList();
    }

    @Override
    public UserDto getByLogin(String login) {
        User user = userDao.getByLogin(login);
        if (user != null) {
            return new UserDto(user.getName(), user.getLastName(), user.getLogin(), user.getImagePath());
        }
        return null;
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
}
