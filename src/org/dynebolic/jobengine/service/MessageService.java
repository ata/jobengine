package org.dynebolic.jobengine.service;

import org.dynebolic.jobengine.entity.Message;
import org.dynebolic.jobengine.entity.User;
import org.dynebolic.jobengine.hibernate.support.EMUtil;

@SuppressWarnings("serial")
public class MessageService extends GenericService<Message> {
	public MessageService() {
		super(Message.class);
	}
	
	public Long unreadCount(User user){
		
		em = EMUtil.createEntityManager();
		query = em.createQuery("select count(*) from Message where messageTo = :user" +
				" and readed = :readed");
		query.setParameter("readed", false);
		query.setParameter("user", user);
		Long count = (Long) query.getSingleResult();
		em.close();
		return count;
		
	}

}
