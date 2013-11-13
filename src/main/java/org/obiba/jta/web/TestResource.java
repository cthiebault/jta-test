package org.obiba.jta.web;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.obiba.jta.service.IntegrationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Transactional
@Component
@Path("/test")
public class TestResource {

  @Resource
  private IntegrationService integrationService;

  @GET
  @Path("/inject")
  @Produces(MediaType.TEXT_PLAIN)
  public String testInjection() {
    return "integrationService: " + integrationService;
  }

  @GET
  @Path("/active")
  @Produces(MediaType.TEXT_PLAIN)
  public String testActiveTransaction() {
    return "isActualTransactionActive: " + TransactionSynchronizationManager.isActualTransactionActive();
  }

  @GET
  @Path("/exception")
  @Produces(MediaType.TEXT_PLAIN)
  public String testException() {
    throw new NullPointerException();
  }
}
