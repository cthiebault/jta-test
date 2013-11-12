/*******************************************************************************
 * Copyright 2008(c) The OBiBa Consortium. All rights reserved.
 *
 * This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.obiba.jta.web.cfg;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.FilterMapping;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.RequestContextFilter;

/**
 *
 */
@Component
public class JettyServer {

  private static final Logger log = LoggerFactory.getLogger(JettyServer.class);

  private static final int HTTP_PORT = 8085;

  private static final int MAX_IDLE_TIME = 30000;

  private static final int REQUEST_HEADER_SIZE = 8192;

  @Autowired
  private ApplicationContext applicationContext;

  private Server jettyServer;

  private ServletContextHandler contextHandler;

  private ConfigurableApplicationContext webApplicationContext;

  @PostConstruct
  public void init() {
    jettyServer = new Server();
    jettyServer.setSendServerVersion(false);
    // OPAL-342: We will manually stop the Jetty server instead of relying its shutdown hook
    jettyServer.setStopAtShutdown(false);

    configureHttpConnector();

    HandlerList handlers = new HandlerList();
    handlers.addHandler(contextHandler = createServletHandler());
    jettyServer.setHandler(handlers);
  }

  private void configureHttpConnector() {
    Connector httpConnector = new SelectChannelConnector();
    httpConnector.setPort(HTTP_PORT);
    httpConnector.setMaxIdleTime(MAX_IDLE_TIME);
    httpConnector.setRequestHeaderSize(REQUEST_HEADER_SIZE);
    jettyServer.addConnector(httpConnector);
  }

  //  @Bean
  public ServletContextHandler getServletContextHandler() {
    return contextHandler;
  }

  public void start() {
    try {
      webApplicationContext.refresh();
      log.info("Starting Opal HTTP/s Server on port {}", jettyServer.getConnectors()[0].getPort());
      jettyServer.start();
    } catch(Exception e) {
      log.error("Error starting jetty", e);
      throw new RuntimeException(e);
    }
  }

  public void stop() {
    try {
      if(webApplicationContext.isActive()) {
        webApplicationContext.close();
      }
    } catch(RuntimeException e) {
      log.warn("Exception during web application context shutdown", e);
    }

    try {
      jettyServer.stop();
    } catch(Exception e) {
      log.warn("Exception during HTTPd server shutdown", e);
    }

  }

  private ServletContextHandler createServletHandler() {
    ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SECURITY);
    handler.setContextPath("/");
    handler.addFilter(new FilterHolder(new RequestContextFilter()), "/*", FilterMapping.DEFAULT);

    initApplicationContext(handler.getServletContext());

    handler.getServletContext()
        .setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, webApplicationContext);

    return handler;
  }

  private void initApplicationContext(ServletContext context) {
    webApplicationContext = new XmlWebApplicationContext();
    webApplicationContext.setParent(applicationContext);
    ((ConfigurableWebApplicationContext) webApplicationContext).setServletContext(context);
    ((AbstractRefreshableConfigApplicationContext) webApplicationContext)
        .setConfigLocation("classpath:application-context.xml");
  }

}
