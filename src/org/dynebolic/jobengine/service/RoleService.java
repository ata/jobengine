package org.dynebolic.jobengine.service;

import java.util.List;

import org.dynebolic.jobengine.entity.Role;
import org.dynebolic.jobengine.hibernate.support.EMUtil;

public class RoleService extends GenericService<Role> {
	public RoleService() {
		super(Role.class);
	}
	public List<Role> find() {
		em = EMUtil.createEntityManager();
		query = em.createQuery("from Role where name != 'Administrator'");
		List<Role> groups = query.getResultList();
		em.close();
		return groups;
	}
}
