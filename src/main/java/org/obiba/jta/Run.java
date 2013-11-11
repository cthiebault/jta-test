package org.obiba.jta;

import org.obiba.jta.web.cfg.JettyServer;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

public class Run {

  private Run() {}

  public static void main(String... args) {
    GenericApplicationContext context = new GenericApplicationContext();
    XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(context);
    xmlReader.loadBeanDefinitions("classpath:application-context.xml");
    context.refresh();
    context.getBean(JettyServer.class).start();
  }

}
