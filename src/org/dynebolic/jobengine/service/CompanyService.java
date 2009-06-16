package org.dynebolic.jobengine.service;

import org.dynebolic.jobengine.entity.Employer;
import org.dynebolic.jobengine.entity.Job;

public class CompanyService extends GenericService<Employer> {
	public CompanyService() {
		super(Employer.class);
	}
}
