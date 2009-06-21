package org.dynebolic.jobengine.service;

import java.util.List;


import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.JobSeekerCertificate;
import org.dynebolic.jobengine.hibernate.support.EMUtil;

@SuppressWarnings("serial")
public class JobSeekerCertificateService extends
		GenericService<JobSeekerCertificate> {
	public JobSeekerCertificateService() {
		super(JobSeekerCertificate.class);
	}

	@SuppressWarnings("unchecked")
	public List<JobSeekerCertificate> find(JobSeeker jobSeeker) {
		em = EMUtil.createEntityManager();
		query = em.createQuery("from JobSeekerCertificate j where j.jobSeeker = :jobSeeker order by j.id");
		query.setParameter("jobSeeker", jobSeeker);
		List<JobSeekerCertificate> certificates = query.getResultList();
		em.close();
		return certificates;
	}
	
	
	
}
