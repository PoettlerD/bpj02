package at.campus02.bp2.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

//	static {
//		try {
//	        sessionFactory = new Configuration()
//	                .configure("/at/campus02/bp2/utils/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
//	                .buildSessionFactory();
//		} catch (Throwable th) {
//			System.err.println("Enitial SessionFactory creation failed" + th);
//			throw new ExceptionInInitializerError(th);
//		}
//	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}