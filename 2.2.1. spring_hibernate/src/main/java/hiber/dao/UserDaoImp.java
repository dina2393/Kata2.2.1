package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user, Car car) {
      user.setCarsId(car);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> getUser(String model, int series) {
      List<User> userList;
      String sqlQuery = "select * from users u where u.cars_id in (select id from cars c where c.model= :model and c.series = :series)";
      userList = sessionFactory.getCurrentSession().createSQLQuery(sqlQuery)
              .setParameter("model", model)
              .setParameter("series", series)
              .addEntity(User.class).getResultList();
      for(User e : userList) {
         System.out.println(e);
      }
      return userList;
   }
}
