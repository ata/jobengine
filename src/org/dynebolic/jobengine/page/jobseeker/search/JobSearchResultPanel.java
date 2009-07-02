package org.dynebolic.jobengine.page.jobseeker.search;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.employer.job.JobPreviewPanel;
import org.dynebolic.jobengine.service.JobService;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class JobSearchResultPanel extends BasePanel {
	private JobService service = new JobService();
	private Integer lastPage;
	private Integer maxResultPage = 10;
	private Integer resultSize;
	private DateFormat dateFormat =  DateFormat.getDateInstance(DateFormat.FULL);
	private Component content;
	
	
	public JobSearchResultPanel(String id, String keywords, BasePage basePage) {
		this(id,keywords,basePage,1);
		
	}
	
	public JobSearchResultPanel(String id, final String keywords, final BasePage basePage, Integer page) {
		super(id);
		int first = 0;
		if(page == 1) {
			first = 0;
		} else {
			first = (page - 1) * maxResultPage;
		}
		
		List<Job> result = service.find(keywords, first , maxResultPage);
		resultSize = service.getResultSize();
		add(new Label("keywords",keywords));
		add(new Label("resultSize",resultSize.toString()));
		
		lastPage = ((Double) Math.ceil((double)resultSize /(double) maxResultPage)).intValue();
		
		System.out.println("first : " + first);
		System.out.println("jumlah halaman : " +lastPage);
		
		
		ListView eachResult = new ListView("eachResult",result) {
		
			@Override
			protected void populateItem(ListItem item) {
				final Job job = (Job) item.getModelObject();
				item.setModel(new CompoundPropertyModel(job));
				final ModalWindow detailModal = new ModalWindow("detailModal");
				item.add(detailModal);
				detailModal.setInitialWidth(780);
				detailModal.setResizable(false);
				detailModal.setCssClassName("w_silver");
				AjaxLink detailLink = new AjaxLink("detailLink"){
					public void onClick(AjaxRequestTarget target) {
						if(getJESession().getRoles().toString().equalsIgnoreCase("JobSeeker")){
							detailModal.setContent(new JobPreviewPanel(detailModal.getContentId(), job.getId()){
	
								@Override
								protected void onClose(AjaxRequestTarget target) {
									detailModal.close(target);
									
								}
								
							});
							detailModal.show(target);
						} else {
							target.appendJavascript("$('notJobSeeker').show().fade({duration:2});");
						}
					}
				};
				item.add(detailLink);
				detailLink.add(new Label("title",job.getTitle()));
				//item.add(new Label("position"));
				item.add(new Label("employer.name"));
				item.add(new Label("category.name"));
				item.add(new Label("location.name"));
				item.add(new Label("experience"));
				item.add(new Label("expired",dateFormat.format(job.getExpired())));
			}
		};
		add(eachResult);
		
		final int previusPage = page -1;
		AjaxLink previousLink = new AjaxLink("previousLink") {
			public void onClick(AjaxRequestTarget target) {
				
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return new JobSearchResultPanel(id, keywords, basePage,previusPage);
					}
				};
				//
				//Panel content = new ApplicantSearchResultPanel("content", keywords, basePage,previusPage);
				content.setOutputMarkupId(true);
				basePage.addOrReplace(content);
				target.addComponent(content);
			}
		};
		add(previousLink);
		if(previusPage < 1) {
			previousLink.setVisible(false);
		}
		
		ArrayList<Integer> pages = new ArrayList<Integer>();
		
		for(int i = 1; i <= lastPage; i++) {
			pages.add(i);
		}
		ListView pageNav = new ListView("pageNav",pages) {
		
			@Override
			protected void populateItem(ListItem item) {
				final Integer pageNum = (Integer) item.getModelObject();
				AjaxLink numPageLink = new AjaxLink("numPageLink") {
					public void onClick(AjaxRequestTarget target) {
						
						content = new AjaxLazyLoadPanel("content"){
							public Component getLazyLoadComponent(String id) {
								return new JobSearchResultPanel("content", 
										keywords, basePage,pageNum);
							}
						};
						
						
						//Panel content = new ApplicantSearchResultPanel("content", 
						//		keywords, basePage,pageNum);
						content.setOutputMarkupId(true);
						basePage.addOrReplace(content);
						target.addComponent(content);
					}
				};
				numPageLink.add(new Label("numPageLabel",pageNum.toString()));
				item.add(numPageLink);
		
			}
		};
		add(pageNav);
		
		final int nextPage = page + 1;
		AjaxLink nextLink = new AjaxLink("nextLink") {
			public void onClick(AjaxRequestTarget target) {
				
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return new JobSearchResultPanel("content", keywords, basePage,nextPage);
					}
				};
				
				//Panel content = new ApplicantSearchResultPanel("content", keywords, basePage,nextPage);
				content.setOutputMarkupId(true);
				basePage.addOrReplace(content);
				target.addComponent(content);
			}
		};
		add(nextLink);
		
		if(nextPage > lastPage) {
			nextLink.setVisible(false);
		}
		
	}
	public void setNavResult(Integer page) {
		
	}
}
