package be.ds.projects.botTrader.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Steven de Cleene
 */
@SpringBootApplication
public class Application {

    public static void main(String... args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        UserRepository userRepository = ctx.getBean("userRepository", UserRepository.class);

        User n = new User();
        n.setName("steve");
        n.setEmail("steve@gmail.com");

        userRepository.save(n);

    }

}
