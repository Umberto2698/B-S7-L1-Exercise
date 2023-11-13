package lezione26.services;

import lezione26.enteties.User;
import lezione26.exceptions.UnauthorizedException;
import lezione26.payloads.users.UserLoginDTO;
import lezione26.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body) {
        User user = userService.findByEmail(body.email());

        if (body.password().equals(user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Email or password invalid.");
        }
    }
}
