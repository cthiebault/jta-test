package org.obiba.jta;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class Dao {

  @Autowired
  private TransactionTemplate transactionTemplate;

  public void createEntity(String name, SessionFactory sessionFactory) {
    Session currentSession = sessionFactory.getCurrentSession();
    Transaction transaction = currentSession.beginTransaction();
    currentSession.save(new Entity(name));
    transaction.commit();
  }

  @SuppressWarnings("unchecked")
  public List<Entity> listEntities(SessionFactory sessionFactory) {
    return (List<Entity>) sessionFactory.getCurrentSession().createCriteria(Entity.class).list();
  }

}
