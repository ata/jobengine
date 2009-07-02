package org.dynebolic.jobengine.page.employer.search;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.Employer;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.JobSeekerSkill;
import org.dynebolic.jobengine.page.BasePage;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.jobseeker.preview.ApplicantPreviewPanel;
import org.dynebolic.jobengine.page.message.MessagePanel;
import org.dynebolic.jobengine.service.EmployerService;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.jobengine.service.JobService;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class ApplicantSearchResultPanel extends BasePanel {
	private JobSeekerService service = new JobSeekerService();
	private EmployerService employerService = new EmployerService();
	private Integer lastPage;
	private Integer maxResultPage = 10;
	private Integer resultSize;
	private Component content;
	public ApplicantSearchResultPanel(String id, String keywords, BasePage basePage) {
		this(id,keywords,basePage,1);
		
	}
	
	public ApplicantSearchResultPanel(String id, final String keywords, final BasePage basePage, Integer page) {
		super(id);
		int first = 0;
		if(page == 1) {
			first = 0;
		} else {
			first = (page - 1) * maxResultPage;
		}
		
		List<JobSeeker> result = service.find(keywords, first , maxResultPage);
		resultSize = service.getResultSize();
		add(new Label("keywords",keywords));
		add(new Label("resultSize",resultSize.toString()));
		
		lastPage = ((Double) Math.ceil((double)resultSize /(double) maxResultPage)).intValue();
		
		
		ListView eachResult = new ListView("eachResult",result) {
		
			@Override
			protected void populateItem(ListItem item) {
				final JobSeeker jobSeeker = (JobSeeker) item.getModelObject();
				item.setModel(new CompoundPropertyModel(jobSeeker));
				
				// detail Link
				
				
				// end detail link
				
				// == misc
				
				final WebMarkupContainer miscContainer = new WebMarkupContainer("miscContainer");
				miscContainer.setOutputMarkupId(true);
				item.add(miscContainer);
				if(!getJESession().getRoles().toString().equalsIgnoreCase("Employer")){
					miscContainer.setVisible(false);
				}
				
				
				final ModalWindow modalMessage = new ModalWindow("modalMessage");
				miscContainer.add(modalMessage);
				modalMessage.setContent(new MessagePanel(modalMessage.getContentId(),jobSeeker.getUser().getId()){

					@Override
					protected void onAjaxSubmit(AjaxRequestTarget target) {
						modalMessage.close(target);
						target.appendJavascript("$('messageSend').show().fade({duration:2});");
						
					}

					@Override
					protected void onCancel(AjaxRequestTarget target) {
						modalMessage.close(target);
						
					}
					
				});
				
				modalMessage.setInitialWidth(780);
				modalMessage.setResizable(false);
				modalMessage.setCssClassName("w_silver");
				
				
				
				AjaxLink messageLink = new AjaxLink("messageLink"){
					@Override
					public void onClick(AjaxRequestTarget target) {
						if(getJESession().getRoles().toString().equalsIgnoreCase("Employer")){
							modalMessage.show(target);
						} else {
							target.appendJavascript("$('notEmployer').show().fade({duration:2});");
						}
					}
				};
				miscContainer.add(messageLink);

				AjaxLink bookmarksLink = new AjaxLink("bookmarksLink"){
					public void onClick(AjaxRequestTarget target) {
						if(getJESession().getRoles().toString().equalsIgnoreCase("Employer")){
							if(getJESession().getUser().getEmployer().getBookmarks().add(jobSeeker)){
								employerService.save(getJESession().getUser().getEmployer());
								target.appendJavascript("$('successBookmarks').show().fade({duration:2});");
							} else {
								target.appendJavascript("$('warningBookmarks').show().fade({duration:2});");
							}
							
							
						} else {
							target.appendJavascript("$('notEmployer').show().fade({duration:2});");
						}
						
					}
				};
				miscContainer.add(bookmarksLink);
				
				final ModalWindow modalDetail = new ModalWindow("modalDetail");
				item.add(modalDetail);
				modalDetail.setInitialWidth(780);
				modalDetail.setResizable(false);
				modalDetail.setCssClassName("w_silver");
				
				
				AjaxLink detailLink = new AjaxLink("detailLink"){
					@Override
					public void onClick(AjaxRequestTarget target) {
						if(getJESession().getRoles().toString().equalsIgnoreCase("Employer")){
							modalDetail.setContent(new ApplicantPreviewPanel(modalDetail.getContentId(), jobSeeker.getId()){

								@Override
								protected void onClose(AjaxRequestTarget target) {
									modalDetail.close(target);
									
								}
								
							});
							modalDetail.show(target);
						} else {
							target.appendJavascript("$('notEmployer').show().fade({duration:2});");
						}
					}
				};
				detailLink.add(new Label("title",jobSeeker.getTitle()));
				item.add(detailLink);
				
				
				item.add(new Label("keySkills"));
				item.add(new Label("currentLocation.name"));
				item.add(new Label("preferPosition"));
				
				
			}
		};
		add(eachResult);
		
		final int previusPage = page -1;
		AjaxLink previousLink = new AjaxLink("previousLink") {
			public void onClick(AjaxRequestTarget target) {
				
				content = new AjaxLazyLoadPanel("content"){
					public Component getLazyLoadComponent(String id) {
						return new ApplicantSearchResultPanel(id, keywords, basePage,previusPage);
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
								return new ApplicantSearchResultPanel("content", 
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
						return new ApplicantSearchResultPanel("content", keywords, basePage,nextPage);
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
