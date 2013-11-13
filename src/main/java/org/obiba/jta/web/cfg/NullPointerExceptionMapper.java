package org.obiba.jta.web.cfg;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

@Component
@Provider
public class NullPointerExceptionMapper implements ExceptionMapper<NullPointerException> {

  @Override
  public Response toResponse(NullPointerException exception) {
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("NullPointerException thrown!").build();
  }
}

