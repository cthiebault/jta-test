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

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JerseyServletConfiguration {

  public static final String WS_ROOT = "/ws";

  @Autowired
  private JettyServer jettyServer;

  @PostConstruct
  public void configureResteasyServlet() {

    ServletContextHandler servletContextHandler = jettyServer.getServletContextHandler();

    ServletHolder servletHolder = new ServletHolder(new ServletContainer());
//    servletHolder.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
//        "com.sun.jersey.api.core.PackagesResourceConfig");
    servletHolder.setInitParameter(ServerProperties.APPLICATION_NAME, "Jersey-test");
    servletHolder.setInitParameter(ServerProperties.PROVIDER_PACKAGES, "org.obiba.jta.web");
    servletHolder.setInitParameter(ServerProperties.TRACING, "ALL");
    servletHolder.setInitParameter(ServerProperties.TRACING_THRESHOLD, "VERBOSE");
    servletHolder
        .setInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters", LoggingFilter.class.getName());
    servletHolder
        .setInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters", LoggingFilter.class.getName());

    servletContextHandler.addServlet(servletHolder, WS_ROOT + "/*");
  }

}
