package org.dynebolic.jobengine.page.employer.job;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.service.IService;
import org.dynebolic.jobengine.service.JobService;
import org.dynebolic.library.JavascriptEventConfirmation;

@SuppressWarnings("serial")
public class JobMasterActionPanel extends Panel{
	
	JobService service = new JobService();
	ModalWindow detailModal;
	ModalWindow editModal;
	
	public JobMasterActionPanel(String componentId, final Job job, final DataTable grid) {
		super(componentId);
		
		
		AjaxLink deleteLink = new AjaxLink("deleteLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				service.remove(job);
				target.addComponent(grid);
			}
		};
		deleteLink.add(new JavascriptEventConfirmation("onclick", "are you sure?"));
		add(deleteLink);
		
		
		editModal = new ModalWindow("editModal");
		AjaxLink editLink = new AjaxLink("editLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				editModal.show(target);
			}
		};
		add(editLink);
		add(editModal);
		editModal.setInitialWidth(780);
		editModal.setResizable(false);
		editModal.setCssClassName("w_silver");
		editModal.setContent(new JobMasterFormPanel(editModal.getContentId(), job.getId()) {
		
			@Override
			protected void onSubmitForm(AjaxRequestTarget target) {
				editModal.close(target);
				target.addComponent(grid);
			}

			@Override
			protected void onCancel(AjaxRequestTarget target) {
				editModal.close(target);
				
			}
		});
		
		
		detailModal = new ModalWindow("detailModal");
		AjaxLink detailLink = new AjaxLink("detailLink"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				detailModal.setContent(
						new JobPreviewPanel(detailModal.getContentId(),job.getId()){

							@Override
							protected void onClose(AjaxRequestTarget target) {
								detailModal.close(target);
								
							}
							
						});
				detailModal.show(target);
			}
		};
		add(detailLink);
		add(detailModal);
		detailModal.setInitialWidth(780);
		detailModal.setResizable(false);
		detailModal.setCssClassName("w_silver");
		//detailModal.setContent(new Label(detailModal.getContentId(),"Test Modal"));
	}

}
