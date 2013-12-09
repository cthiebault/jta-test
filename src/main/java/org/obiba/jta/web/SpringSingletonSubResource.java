package org.obiba.jta.web;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

public interface SpringSingletonSubResource {

  @GET
  @ApiOperation(value = "GET", notes = "SpringSingletonSubResource GET method")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
  Response get();

  @POST
  @ApiOperation(value = "POST", notes = "SpringSingletonSubResource POST method")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
  Response post();
}
