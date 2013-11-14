package org.obiba.jta.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Transactional
@Component
@Path("/transaction")
public class TransactionResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String testActiveTransaction() {
    return "isActualTransactionActive: " + TransactionSynchronizationManager.isActualTransactionActive();
  }

}
