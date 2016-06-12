package de.ollihoo.httptogo;

import org.eclipse.jetty.alpn.server.ALPNServerConnectionFactory;
import org.eclipse.jetty.http2.HTTP2Cipher;
import org.eclipse.jetty.http2.server.HTTP2ServerConnectionFactory;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.stereotype.Component;

@Component
class MyJettyServerCustomizer implements JettyServerCustomizer {

    @Autowired
    ServerProperties serverProperties;

    @Override
    public void customize(Server server) {
        if (serverProperties.getSsl() != null && serverProperties.getSsl().isEnabled()) {
            ServerConnector connector = (ServerConnector) server.getConnectors()[0];
            ConnectionFactory[] connectionFactories = createSslConnectionFacory(connector);

            Connector serverConnector = createConnector(server, connectionFactories, connector.getPort());
            server.setConnectors( new Connector[] {serverConnector});
        }
    }

    private Connector createConnector(Server server, ConnectionFactory[] connectionFactories, int port) {
        ServerConnector serverConnector = new ServerConnector(server, connectionFactories);
        serverConnector.setPort(port);
        return serverConnector;
    }

    private ConnectionFactory[] createSslConnectionFacory(ServerConnector connector) {
        SslContextFactory sslContextFactory = connector.getConnectionFactory(SslConnectionFactory.class).getSslContextFactory();
        HttpConfiguration httpConfiguration = connector.getConnectionFactory(HttpConnectionFactory.class).getHttpConfiguration();

        configureSslContextFactory(sslContextFactory);
        ConnectionFactory[] connectionFactories = createConnectionFactories(sslContextFactory, httpConfiguration);
        return connectionFactories;
    }

    private void configureSslContextFactory(SslContextFactory sslContextFactory) {
        sslContextFactory.setCipherComparator(HTTP2Cipher.COMPARATOR);
        sslContextFactory.setUseCipherSuitesOrder(true);
    }

    private ConnectionFactory[] createConnectionFactories(SslContextFactory sslContextFactory, HttpConfiguration httpConfiguration) {
        SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(sslContextFactory, "alpn");
        ALPNServerConnectionFactory alpnServerConnectionFactory = new ALPNServerConnectionFactory("h2", "h2-17", "h2-16", "h2-15", "h2-14");

        HTTP2ServerConnectionFactory http2ServerConnectionFactory = new HTTP2ServerConnectionFactory(httpConfiguration);

        return new ConnectionFactory[] {sslConnectionFactory, alpnServerConnectionFactory, http2ServerConnectionFactory};
    }
}
