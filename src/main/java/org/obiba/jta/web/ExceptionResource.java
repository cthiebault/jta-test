package org.obiba.jta.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("/exception")
public class ExceptionResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String testException() {
    throw new NullPointerException();
  }
}
