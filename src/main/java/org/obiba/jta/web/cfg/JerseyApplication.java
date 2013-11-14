package org.obiba.jta.web.cfg;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class JerseyApplication extends ResourceConfig {

  public JerseyApplication() {
    packages("org.obiba.jta.web");
    register(RequestContextFilter.class);
    register(LoggingFilter.class);
  }
}
