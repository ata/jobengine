package org.dynebolic.jobengine.service;

import org.dynebolic.jobengine.entity.Country;

@SuppressWarnings("serial")
public class CountryService extends GenericService<Country> {
	public CountryService() {
		super(Country.class);
	}
}
