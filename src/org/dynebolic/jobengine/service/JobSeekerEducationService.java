package org.dynebolic.jobengine.service;

import java.util.List;

import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.JobSeekerEducation;
import org.dynebolic.jobengine.hibernate.support.EMUtil;

@SuppressWarnings("serial")
public class JobSeekerEducationService extends
		GenericService<JobSeekerEducation> {
	public JobSeekerEducationService() {
		super(JobSeekerEducation.class);
	}

	@SuppressWarnings("unchecked")
	public List<JobSeekerEducation> find(JobSeeker jobSeeker) {
		em = EMUtil.createEntityManager();
		query = em.createQuery("from JobSeekerEducation j where j.jobSeeker = :jobSeeker order by j.id");
		query.setParameter("jobSeeker", jobSeeker);
		List<JobSeekerEducation> list = query.getResultList();
		em.close();
		return list;
	}
	public void resetLast(){
		em = EMUtil.createEntityManager();
		em.getTransaction().begin();
		query = em.createQuery("update JobSeekerEducation e set e.last = :last");
		query.setParameter("last", false);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	
	
}
