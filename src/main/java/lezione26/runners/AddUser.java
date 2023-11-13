package lezione26.runners;

import com.github.javafaker.Faker;
import lezione26.payloads.users.UserDTO;
import lezione26.services.DeviceService;
import lezione26.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Order(1)
public class AddUser implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.ITALY);
        for (int i = 0; i < 20; i++) {
            String name = faker.name().firstName();
            String surname = faker.name().lastName();
            String username = faker.funnyName().name();
            String email = name + "." + surname + "@gmail.com";
            UserDTO user = new UserDTO(name, surname, username, email);
            userService.save(user);
        }
    }
}
