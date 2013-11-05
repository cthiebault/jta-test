package org.obiba.jta;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Component
public class Utils {

  private static final int MIN_POOL_SIZE = 3;

  private static final int MAX_POOL_SIZE = 20;

  private static final int MAX_IDLE = 10;

  public DataSource createDataSource() throws PropertyVetoException {
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    dataSource.setDriverClass("org.hsqldb.jdbcDriver");
    dataSource.setJdbcUrl("jdbc:hsqldb:file:jta-test;shutdown=true;hsqldb.tx=mvcc");
    dataSource.setUser("sa");
    dataSource.setPassword("");
    dataSource.setMinPoolSize(MIN_POOL_SIZE);
    dataSource.setMaxPoolSize(MAX_POOL_SIZE);
    dataSource.setMaxIdleTime(MAX_IDLE);
    dataSource.setAutoCommitOnClose(false);
    return dataSource;
  }

}
