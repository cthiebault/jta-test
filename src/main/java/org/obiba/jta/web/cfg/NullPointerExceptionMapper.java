package org.obiba.jta.web.cfg;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Provider
public class NullPointerExceptionMapper implements ExceptionMapper<NullPointerException> {

  private static final Logger log = LoggerFactory.getLogger(NullPointerExceptionMapper.class);

  @Override
  public Response toResponse(NullPointerException exception) {
    log.info(">>> NullPointerExceptionMapper");
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("NullPointerException thrown!").build();
  }
}

