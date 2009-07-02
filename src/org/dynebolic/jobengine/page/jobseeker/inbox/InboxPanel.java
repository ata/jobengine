package org.dynebolic.jobengine.page.jobseeker.inbox;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.dynebolic.jobengine.entity.Job;
import org.dynebolic.jobengine.entity.JobSeeker;
import org.dynebolic.jobengine.entity.Message;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.jobengine.page.employer.job.JobPreviewPanel;
import org.dynebolic.jobengine.page.message.MessageViewPanel;
import org.dynebolic.jobengine.service.JobSeekerService;
import org.dynebolic.jobengine.service.MessageService;
import org.dynebolic.jobengine.service.UserService;
import org.dynebolic.library.JavascriptEventConfirmation;

@SuppressWarnings("serial")
public class InboxPanel extends BasePanel {
	private UserService service = new UserService();
	private MessageService messageService = new MessageService();
	public InboxPanel(String id) {
		super(id);
		List<Message> messages = getJESession().getUser().getInboxMessages();
		ListView eachView = new ListView("eachView",messages){
			@Override
			protected void populateItem(final ListItem item) {
				item.setOutputMarkupId(true);
				final Message message = (Message) item.getModelObject();
				final ModalWindow detailModal = new ModalWindow("detailModal");
				item.add(detailModal);
				detailModal.setInitialWidth(780);
				detailModal.setResizable(false);
				detailModal.setCssClassName("w_silver");
				
				AjaxLink detailLink = new AjaxLink("detailLink"){
					public void onClick(AjaxRequestTarget target) {
						detailModal.setContent(new MessageViewPanel(detailModal.getContentId(),message){
							protected void onClose(AjaxRequestTarget target) {
								target.appendJavascript("$('"+item.getMarkupId()+"').removeClassName('readed_msg)");
								detailModal.close(target);
								
							}
							
						});
						message.setReaded(true);
						messageService.save(message);
						detailModal.show(target);
					}
				};
				detailLink.add(new Label("subject",message.getSubject()));
				item.add(detailLink);
				item.add(new Label("messageFrom.name", message.getMessageFrom().getName()));
				AjaxLink deleteLink = new AjaxLink("deleteLink"){
					public void onClick(AjaxRequestTarget target) {
						if(getJESession().getUser().getInboxMessages().remove(message)){
							messageService.remove(message);
						}
						target.appendJavascript("$('"+item.getMarkupId()+"').addClassName('delete').fade()");
					}
					
				};
				item.add(deleteLink);
				deleteLink.add(new JavascriptEventConfirmation("onclick", "are you sure?"));
				if(!message.isReaded()){
					item.add(new SimpleAttributeModifier("class","readed_msg"));
				}
			}
		};
		
		add(eachView);
		
	}

}
