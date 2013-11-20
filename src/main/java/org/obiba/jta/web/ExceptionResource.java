package org.obiba.jta.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Component
@Path("/exception")
@Api(value = "/exception", description = "Test ExceptionMapper")
public class ExceptionResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @ApiOperation(value = "throws NullPointerException", notes = "More notes about this method", response = String.class)
  public String testException() {
    throw new NullPointerException();
  }
}
