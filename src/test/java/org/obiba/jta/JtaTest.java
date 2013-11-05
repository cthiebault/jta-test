package org.obiba.jta;

import java.beans.PropertyVetoException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@ContextConfiguration("classpath:application-context.xml")
public class JtaTest extends AbstractJUnit4SpringContextTests {

  private static final Logger log = LoggerFactory.getLogger(JtaTest.class);

  @Autowired
  private DataSourceFactory dataSourceFactory;

  @Autowired
  private SessionFactoryFactory sessionFactoryFactory;

  @Autowired
  private Dao dao;

  @Autowired
  private TransactionTemplate transactionTemplate;

  @Test
  public void test() throws PropertyVetoException {

    DataSource dataSource = dataSourceFactory.createDataSource("db1");
    final SessionFactory sessionFactory = sessionFactoryFactory.createSessionFactory(dataSource);

    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus status) {
        dao.createEntity("cedric", sessionFactory);
        List<Entity> entities = dao.listEntities(sessionFactory);
        log.info("entities: {}", entities);
      }
    });

    sessionFactory.close();

  }

}
