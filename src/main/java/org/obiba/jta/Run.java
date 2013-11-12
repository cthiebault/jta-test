package org.obiba.jta;

import org.obiba.jta.web.cfg.JettyServer;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

public class Run {

  private Run() {}

  public static void main(String... args) {

    //  remove existing handlers attached to java.util.logging root logger
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    // add SLF4JBridgeHandler to java.util.logging's root logger
    SLF4JBridgeHandler.install();

    GenericApplicationContext context = new GenericApplicationContext();
    XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(context);
    xmlReader.loadBeanDefinitions("classpath:application-context.xml");
    context.refresh();
    context.getBean(JettyServer.class).start();
  }

}
