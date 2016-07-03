package de.ollihoo.httptogo

import io.undertow.Undertow
import io.undertow.UndertowOptions
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("de.ollihoo")
class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

}
