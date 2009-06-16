package org.dynebolic.jobengine.hibernate.support;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {
	private static final EntityManagerFactory emf;
	static {
	    try {
	      emf = Persistence.createEntityManagerFactory("jobengine");
	      
	    } catch (Throwable ex) {
	      // Make sure you log the exception, as it might be swallowed
	      System.err.println("Initial SessionFactory creation failed." + ex);
	      throw new ExceptionInInitializerError(ex);
	    }
	  }
	public static EntityManager createEntityManager(){
		return emf.createEntityManager();

	}
}
