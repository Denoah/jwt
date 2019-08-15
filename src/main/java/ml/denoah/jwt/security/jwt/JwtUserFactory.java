package ml.denoah.jwt.security.jwt;

import ml.denoah.jwt.core.main.model.Status;
import ml.denoah.jwt.core.user.model.User;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                null
        );
    }
}
