package org.obiba.jta;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:application-context.xml")
public class JtaTest extends AbstractJUnit4SpringContextTests {

  @Autowired
  private Utils utils;

  @Test
  public void test() {

  }

}
