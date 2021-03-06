package org.obiba.jta.service;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Component
public class DataSourceFactory {

  private static final int MIN_POOL_SIZE = 3;

  private static final int MAX_POOL_SIZE = 20;

  private static final int MAX_IDLE = 10;

  public DataSource createDataSource(String database) throws PropertyVetoException {
    ComboPooledDataSource dataSource = new ComboPooledDataSource();
    dataSource.setDriverClass("org.hsqldb.jdbcDriver");
    dataSource.setJdbcUrl("jdbc:hsqldb:file:out/" + database + ";shutdown=true;hsqldb.tx=mvcc");
    dataSource.setUser("sa");
    dataSource.setPassword("");
    dataSource.setMinPoolSize(MIN_POOL_SIZE);
    dataSource.setMaxPoolSize(MAX_POOL_SIZE);
    dataSource.setMaxIdleTime(MAX_IDLE);
    dataSource.setAutoCommitOnClose(false);
    return dataSource;
  }

}
