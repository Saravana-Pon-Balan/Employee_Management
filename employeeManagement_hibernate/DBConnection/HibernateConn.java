package employeeManagement.DBConnection;

import org.hibernate.Session;    
import org.hibernate.SessionFactory;    
import org.hibernate.cfg.Configuration;
/**
 * <p>
 * Implementation of connect to database.
 * </p>
 */

public class HibernateConn {
	private static Session session = null;	
	private static SessionFactory sessionFactory = null;
	/**
   * <p>
   * It creates the connection to database if connection doesn't created yet.
   * </p>
   */
	public static Session getInstance() {
		if(sessionFactory == null  ) {
			Configuration conf  = new Configuration().configure("employeeManagement/employeeMain.cfg.xml");
			sessionFactory= conf.buildSessionFactory();
			return sessionFactory.openSession();
			
		} else {
			return sessionFactory.openSession();
		}
		
	}
}
/*
ANTLR

*/