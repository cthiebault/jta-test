package org.obiba.jta.web;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.obiba.jta.service.IntegrationService;
import org.springframework.stereotype.Component;

@Component
@Path("/inject")
public class InjectionResource {

  @Resource
  private IntegrationService integrationService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String testInjection() {
    return "integrationService: " + integrationService;
  }
}
