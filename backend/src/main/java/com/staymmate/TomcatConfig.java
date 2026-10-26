package com.staymmate;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TomcatConfig {
  @Bean
  WebServerFactoryCustomizer<TomcatServletWebServerFactory> useNio2Connector() {
    return factory -> factory.setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");
  }
}
