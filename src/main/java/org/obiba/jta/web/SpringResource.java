package org.obiba.jta.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Path("/spring")
@Component
@Transactional
public class SpringResource {

  private static final Logger log = LoggerFactory.getLogger(SpringResource.class);

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private SpringSingletonSubResource springSubResource;

  @GET
  public Response get() {
    log.info("get");
    return Response.ok().build();
  }

  @Path("/singleton")
  public SpringSingletonSubResource singleton() {
    log.info("SpringSingletonSubResource: {}", springSubResource);
    return springSubResource;
  }

  @Path("/prototype")
  public SpringPrototypeSubResource prototype() {
    SpringPrototypeSubResource resource = applicationContext.getBean(SpringPrototypeSubResource.class);
    log.info("sub resource: {}", resource);
    return resource;
  }

}
