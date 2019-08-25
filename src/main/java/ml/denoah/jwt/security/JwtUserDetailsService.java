package ml.denoah.jwt.security;

import lombok.extern.slf4j.Slf4j;
import ml.denoah.jwt.core.user.model.User;
import ml.denoah.jwt.core.user.service.UserService;
import ml.denoah.jwt.security.jwt.JwtUser;
import ml.denoah.jwt.security.jwt.JwtUserFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User with username = " + userName + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", userName);
        return jwtUser;
    }
}
