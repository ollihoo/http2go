package de.ollihoo.httptogo

import io.undertow.Undertow
import io.undertow.UndertowOptions
import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UndertowConfiguration {

  @Bean
  UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
    UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory()
    factory.addBuilderCustomizers(new ApplicationUndertowBuilderCustomizer())
    factory
  }


}
