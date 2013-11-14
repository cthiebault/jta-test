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

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

/**
 *
 */
public class JettyServer {

  private static final Logger log = LoggerFactory.getLogger(JettyServer.class);

  private static final int HTTP_PORT = 8085;

  private static final int MAX_IDLE_TIME = 30000;

  private static final int REQUEST_HEADER_SIZE = 8192;

  private final Server jettyServer;

  private final ServletContextHandler servletContextHandler;

  public JettyServer() throws Exception {

    log.info("Configure Jetty Server");
    jettyServer = new Server();
    jettyServer.setSendServerVersion(false);
    jettyServer.setStopAtShutdown(false);

    servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SECURITY);
    servletContextHandler.setContextPath("/");
    servletContextHandler.addEventListener(new RequestContextListener());
    servletContextHandler.addEventListener(new ContextLoaderListener());
    servletContextHandler.setInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, "classpath:application-context.xml");

    createHttpConnector();

    createJerseyServlet();

    HandlerList handlers = new HandlerList();
    handlers.addHandler(servletContextHandler);
    jettyServer.setHandler(handlers);

    jettyServer.start();
    jettyServer.join();
  }

  private void createHttpConnector() {
    Connector httpConnector = new SelectChannelConnector();
    httpConnector.setPort(HTTP_PORT);
    httpConnector.setMaxIdleTime(MAX_IDLE_TIME);
    httpConnector.setRequestHeaderSize(REQUEST_HEADER_SIZE);
    jettyServer.addConnector(httpConnector);
  }

  public void createJerseyServlet() {

    ResourceConfig resourceConfig = new ResourceConfig();
    resourceConfig.packages("org.obiba.jta.web");
    resourceConfig.register(RequestContextFilter.class);
    resourceConfig.property(ServletProperties.PROVIDER_WEB_APP, "true");
    resourceConfig.register(FilterTest.class);

    ServletHolder servletHolder = new ServletHolder(new ServletContainer(resourceConfig));
    servletContextHandler.addServlet(servletHolder, "/*");
  }

}
