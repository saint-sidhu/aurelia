package dev.overlord.aurelia;

import dev.overlord.aurelia.config.AureliaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.security.auth.login.LoginException;

@SpringBootApplication(scanBasePackages = "dev.overlord")
@ComponentScan(basePackages = {"dev.overlord.aurelia"})
@EnableJpaRepositories(basePackages = {"dev.overlord.aurelia.repository"})
public class AureliaApplication {

    public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AureliaApplication.class, args);

        context.getBean(AureliaConfig.class);

    }

}
