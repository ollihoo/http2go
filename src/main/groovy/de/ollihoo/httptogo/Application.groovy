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

    @Bean
    UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory()
        UndertowBuilderCustomizer customizer = createCustomizerWithHttp2()
        factory.addBuilderCustomizers(customizer)
        factory
    }

    private UndertowBuilderCustomizer createCustomizerWithHttp2() {
        new UndertowBuilderCustomizer() {
            @Override
            void customize(Undertow.Builder builder) {
                builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true)
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

}
