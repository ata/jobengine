package org.dynebolic.jobengine.page.jobseeker.profille.carier;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.JobSeekerCarier;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.service.JobSeekerCarierService;
import org.dynebolic.library.JavascriptEventConfirmation;

@SuppressWarnings("serial")
public abstract class CarierListPanel extends BasePanel{
	private JobSeekerCarierService carierService = new JobSeekerCarierService();
	private List<JobSeekerCarier> cariers;
	
	public CarierListPanel(String id) {
		this(id,false);
	}
	
	public CarierListPanel(String id, Boolean reload) {
		super(id);
		if(reload) {
			cariers = carierService.find(getJESession().getUser().getJobSeeker());
		} else {
			cariers = getJESession().getUser().getJobSeeker().getCariers();
		}
		final ListView eachView = new ListView("eachView", cariers){
			protected void populateItem(final ListItem item) {
				final JobSeekerCarier carier = (JobSeekerCarier) item.getModelObject();
				item.setModel(new CompoundPropertyModel(carier));
				item.setOutputMarkupId(true);
				item.add(new Label("institute"));
				item.add(new Label("position"));
				item.add(new Label("start"));
				item.add(new Label("done"));
				
				AjaxLink editLink = new AjaxLink("editLink"){
					public void onClick(AjaxRequestTarget target) {
						onEdit(target, carier);
					}
					
				};
				
				item.add(editLink);
				
				AjaxLink deleteLink = new AjaxLink("deleteLink"){
					public void onClick(AjaxRequestTarget target) {
						carierService.remove(carier);
						target.appendJavascript("$('"+item.getMarkupId()+"').addClassName('delete').fade()");
						//onDeleteItem(target);
					}
					
				};
				deleteLink.add(new JavascriptEventConfirmation("onclick", "are you sure?"));
				item.add(deleteLink);
			}
		};
		add(eachView);
	}
	abstract protected void onDeleteItem(AjaxRequestTarget target);
	protected abstract void onEdit(AjaxRequestTarget target,JobSeekerCarier carier);

}
