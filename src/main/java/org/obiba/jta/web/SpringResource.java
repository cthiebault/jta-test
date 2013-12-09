package org.obiba.jta.web;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/spring")
@Api(value = "/spring", description = "Test resources as Spring bean")
@Component
@Transactional
public class SpringResource {

  private static final Logger log = LoggerFactory.getLogger(SpringResource.class);

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private SpringSingletonSubResource springSubResource;

  @GET
  @ApiOperation("get method")
  @ApiResponses({ @ApiResponse(code = 200, message = "OK") })
  public Response get() {
    log.info("get method");
    return Response.ok().build();
  }

  @GET
  @Path("/user")
  @ApiOperation(value = "Get user", response = UserDto.class)
  public UserDto getUser() {
    log.info("get userDto method");
    return new UserDtoImpl("Cedric", "Thiebault");
  }

  @GET
  @POST
  @Path("/singleton")
  @ApiOperation(value = "/singleton", notes = "Test singleton sub-resources",
      response = SpringSingletonSubResource.class)
  public SpringSingletonSubResource singleton() {
    log.info("SpringSingletonSubResource: {}", springSubResource);
    return springSubResource;
  }

  @Path("/prototype")
  @ApiOperation(value = "/prototype", notes = "Test prototype sub-resources")
  public SpringPrototypeSubResource prototype() {
    SpringPrototypeSubResource resource = applicationContext.getBean(SpringPrototypeSubResource.class);
    log.info("sub resource: {}", resource);
    return resource;
  }

}
