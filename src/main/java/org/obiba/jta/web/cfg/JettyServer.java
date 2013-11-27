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
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.plugins.spring.SpringContextLoaderListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextListener;

public class JettyServer {

  private static final int HTTP_PORT = 8085;

  private static final int MAX_IDLE_TIME = 30000;

  private static final int REQUEST_HEADER_SIZE = 8192;

  private final Server jettyServer;

  private final ServletContextHandler servletContextHandler;

  public JettyServer() throws Exception {

    jettyServer = new Server();
    jettyServer.setSendServerVersion(false);
    jettyServer.setStopAtShutdown(false);

    servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SECURITY);
    servletContextHandler.setContextPath("/");
    // listeners order is important!
    servletContextHandler.addEventListener(new ResteasyBootstrap());
    servletContextHandler.addEventListener(new SpringContextLoaderListener());
    servletContextHandler.addEventListener(new RequestContextListener());
    servletContextHandler.setInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, "classpath:application-context.xml");

    createHttpConnector();

    createRestEasyServlet();

    HandlerList handlers = new HandlerList();
    handlers.addHandler(servletContextHandler);
    jettyServer.setHandler(handlers);

    jettyServer.start();
  }

  private void createHttpConnector() {
    Connector httpConnector = new SelectChannelConnector();
    httpConnector.setPort(HTTP_PORT);
    httpConnector.setMaxIdleTime(MAX_IDLE_TIME);
    httpConnector.setRequestHeaderSize(REQUEST_HEADER_SIZE);
    jettyServer.addConnector(httpConnector);
  }

  private void createRestEasyServlet() {
    ServletHolder servletHolder = new ServletHolder(new HttpServletDispatcher());
    servletHolder.setInitParameter("resteasy.servlet.mapping.prefix", "/ws");
    servletHolder.setInitOrder(1);
    servletContextHandler.addServlet(servletHolder, "/ws/*");
  }

}
