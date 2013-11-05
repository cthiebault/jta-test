package org.obiba.jta;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.mchange.v2.c3p0.PooledDataSource;

@Component
public class IntegrationService {

  private static final Logger log = LoggerFactory.getLogger(IntegrationService.class);

  @Autowired
  private DataSourceFactory dataSourceFactory;

  @Autowired
  private SessionFactoryFactory sessionFactoryFactory;

  @Autowired
  private Dao dao;

  @Autowired
  private TransactionTemplate transactionTemplate;

  public void createEntities(final String database) throws Exception {
    log.info("Create database {}", database);

    DataSource dataSource = dataSourceFactory.createDataSource(database);
    final SessionFactory sessionFactory = sessionFactoryFactory.createSessionFactory(dataSource);

    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus status) {
        dao.createEntity(database, sessionFactory);
        List<Entity> entities = dao.listEntities(sessionFactory);
        log.info("entities: {}", entities);
      }
    });

    sessionFactory.close();
    ((PooledDataSource) dataSource).close();
  }

}
