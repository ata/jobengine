package org.dynebolic.jobengine.service;

import org.dynebolic.jobengine.entity.Employer;

@SuppressWarnings("serial")
public class CompanyService extends GenericService<Employer> {
	public CompanyService() {
		super(Employer.class);
	}
}
