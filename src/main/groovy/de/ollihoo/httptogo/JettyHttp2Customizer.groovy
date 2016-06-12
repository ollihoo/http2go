package de.ollihoo.httptogo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory
import org.springframework.stereotype.Component

@Component
public class JettyHttp2Customizer implements EmbeddedServletContainerCustomizer {

    private final ServerProperties serverProperties

    @Autowired
    private MyJettyServerCustomizer jettyServerCustomizer

    @Autowired
    public JettyHttp2Customizer(ServerProperties serverProperties) {
        this.serverProperties = serverProperties
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        JettyEmbeddedServletContainerFactory factory = (JettyEmbeddedServletContainerFactory) container
        factory.addServerCustomizers(jettyServerCustomizer)
    }

}