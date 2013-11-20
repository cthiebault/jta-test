package org.obiba.jta.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Transactional
@Component
@Path("/transaction")
@Api(value = "/transaction", description = "Test @Transactional in resources")
public class TransactionResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @ApiOperation(value = "isActualTransactionActive", notes = "More notes about this method", response = String.class)
  public String testActiveTransaction() {
    return "isActualTransactionActive: " + TransactionSynchronizationManager.isActualTransactionActive();
  }

}
