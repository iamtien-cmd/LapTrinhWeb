package vn.iotstar.configs;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
@PersistenceContext
public class JpaConfig {
 public static EntityManager getEntityManager() {
 EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-sql");
 return factory.createEntityManager();
 }
 public static void main(String[] args) {
	 EntityManager enma = JpaConfig.getEntityManager();
	 EntityTransaction trans = enma.getTransaction();
	 
	 try {
	 trans.begin();
	
	 trans.commit();
	 } catch (Exception e) {
	 e.printStackTrace();
	 trans.rollback();
	 throw e;
	 }finally {
	 enma.close();
	 }
	 }
}
