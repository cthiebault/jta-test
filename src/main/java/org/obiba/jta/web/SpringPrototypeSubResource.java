package org.obiba.jta.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

public interface SpringPrototypeSubResource {

  @GET
  @ApiOperation(value = "SpringPrototypeSubResource", notes = "More notes about this method")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
  Response get();

  @Path("/prototype")
  @ApiOperation(value = "/prototype", notes = "More notes about this method")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
  SpringPrototypeSubResource prototype();
}
