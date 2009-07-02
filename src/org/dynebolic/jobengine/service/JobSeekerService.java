package org.dynebolic.jobengine.service;

import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.hibernate.support.EMUtil;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;

@SuppressWarnings("serial")
public class JobSeekerService extends GenericService<JobSeeker> {
	private Integer resultSize;
	public JobSeekerService() {
		super(JobSeeker.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<JobSeeker> find(String keywords, int first, int count) {
		if(keywords == null) keywords = "xxxxxxxxxxxxxx";
		em = EMUtil.createEntityManager();
		FullTextEntityManager fullTextEntityManager = 
			Search.createFullTextEntityManager(em);
		String[] defaultKey = {"title","gender","currentLocation.name",
				"category.name","country.name","keySkills","preferPosition"};
		MultiFieldQueryParser parser = new MultiFieldQueryParser(defaultKey,
				  new StandardAnalyzer());
		
		org.apache.lucene.search.Query luceneQuery = null;
		try {
			luceneQuery = parser.parse(keywords);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FullTextQuery fullTextQuery = 
			fullTextEntityManager.createFullTextQuery(luceneQuery, JobSeeker.class);
		setResultSize(fullTextQuery.getResultSize());
		fullTextQuery.setFirstResult(first);
		fullTextQuery.setMaxResults(count);
		List<JobSeeker> jobseekers = (List<JobSeeker>) fullTextQuery.getResultList();
		return jobseekers;
		
	}

	/**
	 * @param resultSize the resultSize to set
	 */
	public void setResultSize(Integer resultSize) {
		this.resultSize = resultSize;
	}

	/**
	 * @return the resultSize
	 */
	public Integer getResultSize() {
		return resultSize;
	}
}
