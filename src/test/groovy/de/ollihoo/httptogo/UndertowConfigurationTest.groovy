package de.ollihoo.httptogo

import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory
import spock.lang.Specification

class UndertowConfigurationTest extends Specification {

  private UndertowConfiguration configuration = new UndertowConfiguration()

  def "Configuration returns a container factory" () {
    when:
    def factory = configuration.embeddedServletContainerFactory()

    then:
    factory instanceof UndertowEmbeddedServletContainerFactory
  }

  def "Factory contains one builderCustomizer" () {
    when:
    def factory = configuration.embeddedServletContainerFactory()

    then:
    factory.getBuilderCustomizers().size() == 1
  }

  def "Factory contains specialized builderCustomizer" () {
    when:
    def factory = configuration.embeddedServletContainerFactory()
    def customizers = factory.getBuilderCustomizers()

    then:
    customizers[0] instanceof ApplicationUndertowBuilderCustomizer
  }

}
