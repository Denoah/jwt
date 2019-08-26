package ml.denoah.jwt.rest;

import ml.denoah.jwt.core.user.model.User;
import ml.denoah.jwt.core.user.service.UserService;
import ml.denoah.jwt.core.user.view.UserView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestController {
    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<UserView> getUserById(@PathVariable(name = "id")  Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(UserView.fromUser(user), HttpStatus.OK);
    }
}
