package de.ollihoo.httptogo

import io.undertow.Undertow
import io.undertow.UndertowOptions
import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer

class ApplicationUndertowBuilderCustomizer implements UndertowBuilderCustomizer {
  @Override
  void customize(Undertow.Builder builder) {
    builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true)
  }
}
