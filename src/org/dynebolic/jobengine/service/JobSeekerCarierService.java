package org.dynebolic.jobengine.service;

import java.util.List;

import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.JobSeekerCarier;
import org.dynebolic.jobengine.hibernate.support.EMUtil;

@SuppressWarnings("serial")
public class JobSeekerCarierService extends
		GenericService<JobSeekerCarier> {
	public JobSeekerCarierService() {
		super(JobSeekerCarier.class);
	}

	@SuppressWarnings("unchecked")
	public List<JobSeekerCarier> find(JobSeeker jobSeeker) {
		em = EMUtil.createEntityManager();
		query = em.createQuery("from JobSeekerCarier j where j.jobSeeker = :jobSeeker order by j.id");
		query.setParameter("jobSeeker", jobSeeker);
		List<JobSeekerCarier> cariers = query.getResultList();
		em.close();
		return cariers;
	}
	
	
	
}
