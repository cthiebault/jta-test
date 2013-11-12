package org.obiba.jta.web.cfg;

import java.io.IOException;

import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Provider
//@Component
public class InterceptorTest implements WriterInterceptor, ReaderInterceptor {

  private static final Logger log = LoggerFactory.getLogger(InterceptorTest.class);

  @Override
  public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
    log.info(">> context: {}", context);
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
    log.info(">> context: {}", context);
  }

  public static class Binder extends AbstractBinder {

    @Override
    protected void configure() {
      bind(InterceptorTest.class).to(ReaderInterceptor.class).to(WriterInterceptor.class).in(Singleton.class);
    }
  }
}
