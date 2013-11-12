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
import javax.servlet.ServletContextEvent;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.plugins.spring.SpringContextLoaderSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;

@Component
public class ResteasyServletConfiguration {

  public static final String WS_ROOT = "/ws";

  @Autowired
  private JettyServer jettyServer;

  @PostConstruct
  public void configureResteasyServlet() {

    ServletContextHandler servletContextHandler = jettyServer.getServletContextHandler();

    servletContextHandler.addEventListener(new ResteasyBootstrap());
    servletContextHandler.addEventListener(new RestEasySpringContextLoaderListener());
//    servletContextHandler.addEventListener(new SpringContextLoaderListener());

    ServletHolder servletHolder = new ServletHolder(new HttpServletDispatcher());
    servletHolder.setInitParameter("resteasy.servlet.mapping.prefix", WS_ROOT);
    servletContextHandler.addServlet(servletHolder, WS_ROOT + "/*");
  }

  public static class RestEasySpringContextLoaderListener extends ContextLoaderListener {

    private final SpringContextLoaderSupport springContextLoaderSupport = new SpringContextLoaderSupport();

    @Override
    public void contextInitialized(ServletContextEvent event) {

    }

    @Override
    protected void customizeContext(ServletContext servletContext,
        ConfigurableWebApplicationContext configurableWebApplicationContext) {
      super.customizeContext(servletContext, configurableWebApplicationContext);
      springContextLoaderSupport.customizeContext(servletContext, configurableWebApplicationContext);
    }
  }

  public class MyCustomSpringContextLoader extends ContextLoader {
    private final SpringContextLoaderSupport springContextLoaderSupport = new SpringContextLoaderSupport();

    @Override
    protected void customizeContext(ServletContext servletContext,
        ConfigurableWebApplicationContext configurableWebApplicationContext) {
      super.customizeContext(servletContext, configurableWebApplicationContext);

      // Your custom code

      springContextLoaderSupport.customizeContext(servletContext, configurableWebApplicationContext);

      // Your custom code
    }
  }
}
