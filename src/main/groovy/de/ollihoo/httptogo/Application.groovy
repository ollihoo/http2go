package de.ollihoo.httptogo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("de.ollihoo")
class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
