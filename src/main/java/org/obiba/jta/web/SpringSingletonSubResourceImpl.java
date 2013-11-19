package org.obiba.jta.web;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SpringSingletonSubResourceImpl implements SpringSingletonSubResource {

  @Override
  public Response get() {
    return Response.ok().build();
  }

}
