package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public void saveUser(User user);
    public User getUserById(long id);
    public List<User> getAllUsers();
    public void editUser(User user);
   // public void deleteUser(long id);
   public void deleteUser(String name);
    public User findUserByName(String name);
}

