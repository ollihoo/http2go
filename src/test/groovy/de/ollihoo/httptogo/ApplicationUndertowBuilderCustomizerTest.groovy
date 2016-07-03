package de.ollihoo.httptogo

import io.undertow.Undertow
import io.undertow.UndertowOptions
import spock.lang.Specification

class ApplicationUndertowBuilderCustomizerTest extends Specification {

  private ApplicationUndertowBuilderCustomizer customizer = new ApplicationUndertowBuilderCustomizer()

  def "Undertow gets server option ENABLE_HTTP2 set on true" () {
    given:
    Undertow.Builder builder = Undertow.builder()
    def globalBuilderSpy = GroovySpy(Undertow.Builder, global: true)

    when:
    customizer.customize(builder)

    then:
    1 * globalBuilderSpy.setServerOption(UndertowOptions.ENABLE_HTTP2, true)
  }
}
