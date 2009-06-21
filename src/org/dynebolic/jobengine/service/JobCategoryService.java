package org.dynebolic.jobengine.service;

import org.dynebolic.jobengine.entity.JobCategory;

@SuppressWarnings("serial")
public class JobCategoryService extends GenericService<JobCategory> {
	public JobCategoryService() {
		super(JobCategory.class);
	}
}
