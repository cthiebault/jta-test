package org.obiba.jta.web;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

public interface SpringSingletonSubResource {
  @GET
  Response get();
}
