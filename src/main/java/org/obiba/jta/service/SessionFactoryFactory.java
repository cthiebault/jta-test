package org.obiba.jta.service;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Environment;
import org.obiba.jta.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.jta.JtaTransactionManager;

@Component
public class SessionFactoryFactory {

  @Autowired
  private JtaTransactionManager transactionManager;

  @Autowired
  private ApplicationContext applicationContext;

  public SessionFactory createSessionFactory(DataSource dataSource) {
    LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
    factory.setDataSource(dataSource);
    // will set
    // hibernate.transaction.jta.platform=
    // hibernate.transaction.factory_class=org.hibernate.engine.transaction.internal.jta.CMTTransactionFactory
    factory.setJtaTransactionManager(transactionManager);
    factory.setAnnotatedClasses(new Class<?>[] { Entity.class });
    factory.getHibernateProperties().setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
    factory.getHibernateProperties().setProperty(AvailableSettings.USE_STRUCTURED_CACHE, "true");
    factory.getHibernateProperties().setProperty(AvailableSettings.USE_QUERY_CACHE, "true");
    factory.getHibernateProperties().setProperty(AvailableSettings.USE_SECOND_LEVEL_CACHE, "true");
    factory.getHibernateProperties()
        .setProperty(Environment.CACHE_REGION_FACTORY, "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
    factory.getHibernateProperties().setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "jta");
    factory.getHibernateProperties().setProperty(AvailableSettings.AUTO_CLOSE_SESSION, "true");
    factory.getHibernateProperties().setProperty(AvailableSettings.FLUSH_BEFORE_COMPLETION, "true");

    // Inject dependencies
    factory = (LocalSessionFactoryBean) applicationContext.getAutowireCapableBeanFactory()
        .initializeBean(factory, dataSource.hashCode() + "-sessionFactory");

    return factory.getObject();
  }

}
