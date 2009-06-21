package org.dynebolic.jobengine.service;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.dynebolic.jobengine.entity.User;
import org.dynebolic.jobengine.hibernate.support.EMUtil;


@SuppressWarnings("serial")
public class UserService extends GenericService<User>{
	public UserService() {
		super(User.class);
	}

	public User authenticate(String username, String password)
	{
		em = EMUtil.createEntityManager();
		Query q = em.createQuery("from User u " +
				"where (u.username = :username AND u.password = :password)" +
				"OR (u.email = :username AND u.password = :password)");
		q.setParameter("password", password);
		q.setParameter("username",username);
		User user = null; 
			
		try{
			user = (User) q.getSingleResult();
		} catch (NoResultException e) {
			
		}
		
		return user;
	}
	
	public Boolean validUsername(String username) {
		em = EMUtil.createEntityManager();
		query = em.createQuery("select count(*) from User where username = :username");
		query.setParameter("username", username);
		Long count = (Long) query.getSingleResult();
		System.out.print("username :" +username +", count:" +count);
		em.close();
		if(count > 0) {
			return false;
		} else {
			return true;
		}
		
		
	}
	
	public Boolean validEmail(String email) {
		em = EMUtil.createEntityManager();
		query = em.createQuery("select count(*) from User where email = :email");
		query.setParameter("email", email);
		Long count = (Long) query.getSingleResult();
		System.out.print("email :" +email +", count:" +count);
		em.close();
		if(count > 0) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public void refresh(User user) {
		em = EMUtil.createEntityManager();
		em.getTransaction().begin();
		em.refresh(user);
		em.getTransaction().commit();
		em.close();
	}
}
