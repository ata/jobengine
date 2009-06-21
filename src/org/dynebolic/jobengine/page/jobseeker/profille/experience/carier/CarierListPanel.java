package org.dynebolic.jobengine.page.jobseeker.profille.experience.carier;

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

@SuppressWarnings("serial")
public abstract class CarierListPanel extends BasePanel{
	private JobSeekerCarierService carierService = new JobSeekerCarierService();
	private List<JobSeekerCarier> cariers;
	public CarierListPanel(String id) {
		super(id);
		cariers = carierService.find(getJESession().getUser().getJobSeeker());
		final ListView eachView = new ListView("eachView", cariers){
			protected void populateItem(final ListItem item) {
				final JobSeekerCarier carier = (JobSeekerCarier) item.getModelObject();
				item.setModel(new CompoundPropertyModel(carier));
				item.add(new Label("institute"));
				item.add(new Label("position"));
				item.add(new Label("start"));
				item.add(new Label("done"));
				item.add(new AjaxLink("deleteLink"){
					public void onClick(AjaxRequestTarget target) {
						carierService.remove(carier);
						onDeleteItem(target);
					}
					
				});
			}
		};
		add(eachView);
	}
	abstract protected void onDeleteItem(AjaxRequestTarget target);

}
