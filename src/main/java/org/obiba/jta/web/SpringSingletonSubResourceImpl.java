package org.obiba.jta.web;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SpringSingletonSubResourceImpl implements SpringSingletonSubResource {

  private static final Logger log = LoggerFactory.getLogger(SpringSingletonSubResourceImpl.class);

  @Override
  public Response get() {
    log.debug("Spring singleton sub-resource");
    return Response.ok().build();
  }

  @Override
  public Response post() {
    log.debug("Spring singleton sub-resource");
    return Response.ok().build();
  }

}
