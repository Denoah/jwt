package ml.denoah.jwt.core.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import ml.denoah.jwt.core.main.model.Status;
import ml.denoah.jwt.core.role.model.Role;
import ml.denoah.jwt.core.role.repository.RoleRepository;
import ml.denoah.jwt.core.user.model.User;
import ml.denoah.jwt.core.user.repository.UserRepository;
import ml.denoah.jwt.core.user.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User savedUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", savedUser);

        return savedUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("IN getAll - {} users found", users.size());
        return users;
    }

    @Override
    public User findByUsername(String userName) {
        User user = userRepository.findByUsername(userName);
        log.info("IN findByUsername - user: {} found by userName = {}", user, userName);
        return user;
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        log.info("IN findById - user: {} found by id = {}", user.orElse(null), id);
        return user.orElse(null);
    }

    @Override
    public void delete(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus(Status.DELETED);
            userRepository.save(user);

            log.info("IN delete - user: {} deleted by id = {}", user, id);
        } else {
            log.warn("IN delete - user not found by id = {}", id);
        }
    }
}
