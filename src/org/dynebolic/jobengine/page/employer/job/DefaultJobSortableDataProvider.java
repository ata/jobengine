package org.dynebolic.jobengine.page.employer.job;

import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.page.administrator.generic.GenericSortableDataProvider;
import org.dynebolic.jobengine.service.JobService;

public class DefaultJobSortableDataProvider 
extends GenericSortableDataProvider<Job> {

	public DefaultJobSortableDataProvider() {
		super(Job.class);
		setService(new JobService());
	}
}
