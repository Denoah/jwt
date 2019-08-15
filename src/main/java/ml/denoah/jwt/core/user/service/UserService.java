package ml.denoah.jwt.core.user.service;

import ml.denoah.jwt.core.user.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByUsername(String userName);

    User findById(Long id);

    void delete(Long id);
}
