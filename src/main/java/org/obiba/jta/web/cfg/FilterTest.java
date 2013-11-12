package org.obiba.jta.web.cfg;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ExtendedUriInfo;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@Priority(Integer.MAX_VALUE)
public class FilterTest implements ContainerRequestFilter {

  private static final Logger log = LoggerFactory.getLogger(FilterTest.class);

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    ResourceMethod method = ((ExtendedUriInfo) requestContext.getUriInfo()).getMatchedResourceMethod();
    log.info(">> method: {}", method);
    log.info(">> getHandlingMethod: {}", method.getInvocable().getHandlingMethod());
  }

}
