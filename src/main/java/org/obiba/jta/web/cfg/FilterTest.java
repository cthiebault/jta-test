package org.obiba.jta.web.cfg;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.obiba.jta.service.IntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Provider
@Component
public class FilterTest implements PreProcessInterceptor {

  private static final Logger log = LoggerFactory.getLogger(FilterTest.class);

  @Autowired
  private IntegrationService integrationService;

  @Override
  public ServerResponse preProcess(HttpRequest request, ResourceMethodInvoker method)
      throws Failure, WebApplicationException {
    log.info(">>> integrationService: {}", integrationService);
    log.info(">>> method: {}", method);

    return null;
  }
}
