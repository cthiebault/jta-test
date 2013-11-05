package org.obiba.jta;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class SessionFactoryFactory {

  @Autowired
  private TransactionManager jtaTransactionManager;

  @Autowired
  private ApplicationContext applicationContext;

  public SessionFactory createSessionFactory(DataSource dataSource) {
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setJtaTransactionManager(jtaTransactionManager);
    factoryBean.getHibernateProperties().setProperty(Environment.HBM2DDL_AUTO, "update");
    factoryBean.getHibernateProperties().setProperty(Environment.USE_STRUCTURED_CACHE, "true");
    factoryBean.getHibernateProperties().setProperty(Environment.USE_QUERY_CACHE, "true");
    factoryBean.getHibernateProperties().setProperty(Environment.USE_SECOND_LEVEL_CACHE, "true");
    factoryBean.getHibernateProperties()
        .setProperty(Environment.CACHE_REGION_FACTORY, "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
    factoryBean.getHibernateProperties().setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "jta");
    factoryBean.getHibernateProperties().setProperty(Environment.AUTO_CLOSE_SESSION, "true");
    factoryBean.getHibernateProperties().setProperty(Environment.FLUSH_BEFORE_COMPLETION, "true");
    factoryBean.setAnnotatedClasses(new Class<?>[] { Entity.class });

    // Inject dependencies
    factoryBean = (LocalSessionFactoryBean) applicationContext.getAutowireCapableBeanFactory()
        .initializeBean(factoryBean, dataSource.hashCode() + "-sessionFactory");

    return factoryBean.getObject();
  }

}
