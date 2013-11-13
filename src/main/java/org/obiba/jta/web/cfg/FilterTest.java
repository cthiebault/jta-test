package org.obiba.jta.web.cfg;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ExtendedUriInfo;
import org.glassfish.jersey.server.model.Invocable;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.glassfish.jersey.server.model.ResourceModelComponent;
import org.obiba.jta.service.IntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Provider
@Component
public class FilterTest implements ContainerRequestFilter {

  private static final Logger log = LoggerFactory.getLogger(FilterTest.class);

  @Autowired
  private IntegrationService integrationService;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {

    ResourceMethod method = ((ExtendedUriInfo) requestContext.getUriInfo()).getMatchedResourceMethod();
    log.info("integrationService: {}", integrationService);
    log.info("method: {}", method);
    Invocable invocable = method.getInvocable();
    log.info("getHandlingMethod: {}", invocable.getHandlingMethod());
    log.info("parent: {}", method.getParent());
    for(ResourceModelComponent resourceModelComponent : invocable.getComponents()) {
      log.info("  resourceModelComponent: {}", resourceModelComponent);
      log.info("  class: {}", resourceModelComponent.getClass());
    }
    log.info("handler: {}", invocable.getHandler().getHandlerClass());
  }

}
