package org.obiba.jta.web;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Transactional
public class SpringPrototypeSubResourceImpl implements SpringPrototypeSubResource {

  private static final Logger log = LoggerFactory.getLogger(SpringPrototypeSubResourceImpl.class);

  @Override
  public Response get() {
    log.debug("Spring prototype sub-resource");
    return Response.ok().build();
  }

}
