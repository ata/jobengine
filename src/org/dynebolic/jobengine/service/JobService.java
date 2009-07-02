package org.dynebolic.jobengine.service;

import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.dynebolic.jobengine.JobEngineAuthenticatedWebSession;
import org.dynebolic.jobengine.entity.Employer;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.hibernate.support.EMUtil;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;

@SuppressWarnings("serial")
public class JobService extends GenericService<Job> {
	private Integer resultSize;
	
	public JobService() {
		super(Job.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> find(Integer first, Integer count, String orderColumn,
			Boolean ascending) {
		
		Employer employer = ((JobEngineAuthenticatedWebSession) 
				JobEngineAuthenticatedWebSession.get()).getUser().getEmployer();
		
		// TODO Auto-generated method stub
		em = EMUtil.createEntityManager();
		StringBuilder squery = new StringBuilder();
		squery.append("select job from Job job where job.employer = :employer");
		squery.append(" order by").append(" job.").append(orderColumn);
		if(ascending) squery.append(" asc");
		else squery.append(" desc");
		query = em.createQuery(squery.toString());
		query.setParameter("employer", employer);
		query.setFirstResult(first);
		query.setMaxResults(count);
		List<Job> list = query.getResultList();
		em.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Job> find(String keywords, int first, int count) {
		if(keywords == null) keywords = "xxxxxxxxxxxxxx";
		em = EMUtil.createEntityManager();
		FullTextEntityManager fullTextEntityManager = 
			Search.createFullTextEntityManager(em);
		String[] defaultKey = {"title","expired","position","description","ipk",
				"salary","experience","location.name", "employer.name",
				"category.name","education.name"};
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
			fullTextEntityManager.createFullTextQuery(luceneQuery, Job.class);
		setResultSize(fullTextQuery.getResultSize());
		fullTextQuery.setFirstResult(first);
		fullTextQuery.setMaxResults(count);
		List<Job> jobs = (List<Job>) fullTextQuery.getResultList();
		System.out.println("Jobs: " + jobs);
		return jobs;
		
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
