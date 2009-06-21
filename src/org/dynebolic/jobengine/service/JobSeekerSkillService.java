package org.dynebolic.jobengine.service;

import java.util.List;

import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.JobSeekerSkill;
import org.dynebolic.jobengine.hibernate.support.EMUtil;

@SuppressWarnings("serial")
public class JobSeekerSkillService extends	GenericService<JobSeekerSkill> {
	public JobSeekerSkillService() {
		super(JobSeekerSkill.class);
	}

	@SuppressWarnings("unchecked")
	public List<JobSeekerSkill> find(JobSeeker jobSeeker) {
		em = EMUtil.createEntityManager();
		query = em.createQuery("from JobSeekerSkill j " +
				"where j.jobSeeker = :jobSeeker order by j.id");
		query.setParameter("jobSeeker", jobSeeker);
		List<JobSeekerSkill> list = query.getResultList();
		em.close();
		return list;
	}
	
	
}
