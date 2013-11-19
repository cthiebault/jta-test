package org.obiba.jta.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public interface SpringPrototypeSubResource {

  @GET
  Response get();

  @Path("/prototype")
  SpringPrototypeSubResource prototype();
}
