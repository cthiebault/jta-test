package org.obiba.jta.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Transactional
@Component
@Path("/test")
public class TestResource {

  @GET
  @Path("/active")
  public String isTransactionActive() {
    return "isActualTransactionActive: " + TransactionSynchronizationManager.isActualTransactionActive();
  }

}
