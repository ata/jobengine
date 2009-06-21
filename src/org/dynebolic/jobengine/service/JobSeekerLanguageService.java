package org.dynebolic.jobengine.service;

import java.util.List;

import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.JobSeekerLanguage;
import org.dynebolic.jobengine.hibernate.support.EMUtil;

@SuppressWarnings("serial")
public class JobSeekerLanguageService extends	GenericService<JobSeekerLanguage> {
	public JobSeekerLanguageService() {
		super(JobSeekerLanguage.class);
	}

	@SuppressWarnings("unchecked")
	public List<JobSeekerLanguage> find(JobSeeker jobSeeker) {
		em = EMUtil.createEntityManager();
		query = em.createQuery("from JobSeekerLanguage j " +
				"where j.jobSeeker = :jobSeeker order by j.id");
		query.setParameter("jobSeeker", jobSeeker);
		List<JobSeekerLanguage> list = query.getResultList();
		em.close();
		return list;
	}
	
	
}
