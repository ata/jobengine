package org.dynebolic.jobengine.page.auth.account;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.dynebolic.jobengine.page.BasePanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

@SuppressWarnings("serial")
public class AccountPanel extends BasePanel {
	
	private WebMarkupContainer emailContainer;
	private WebMarkupContainer passwordContainer;
	private Component passwordPanel;
	public AccountPanel(String id) {
		
		super(id);
		
		add(new Label("username",getJESession().getUser().getUsername()));
		emailContainer = new WebMarkupContainer("emailContainer");
		emailContainer.setOutputMarkupId(true);
		add(emailContainer);
		emailContainer.add(new Label("emailPanel",getJESession().getUser().getEmail()));
		emailContainer.add(getEditEmailLink());
		
		passwordContainer = new WebMarkupContainer("passwordContainer");
		passwordContainer.setOutputMarkupId(true);
		add(passwordContainer);
		
		passwordContainer.add(getChangePasswordLink());
		passwordPanel = getPasswordPanel();
		passwordPanel.setVisible(false);
		passwordContainer.add(passwordPanel);
		
	}
	
	private Component getPasswordPanel(){
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("passwordPanel"){

			@Override
			public Component getLazyLoadComponent(String id) {
				return new ChangePasswordPanel(id){

					@Override
					protected void onAjaxSubmit(AjaxRequestTarget target) {
						passwordPanel.setVisible(false);
						target.addComponent(passwordContainer);
						target.appendJavascript("$('changed').show().fade({duration:2});");
					}

					@Override
					protected void onCancel(AjaxRequestTarget target) {
						passwordPanel.setVisible(false);
						target.addComponent(passwordContainer);
						
					}
					
				};
			}
		};
		load.setOutputMarkupId(true);
		return load;
	}
	
	private Component getEmailPanel(){
		AjaxLazyLoadPanel load = new AjaxLazyLoadPanel("emailPanel"){

			@Override
			public Component getLazyLoadComponent(String id) {
				return new ChangeEmailPanel(id){

					@Override
					protected void onAjaxSubmit(AjaxRequestTarget target) {
						emailContainer.addOrReplace(
								new Label("emailPanel",getJESession().getUser().getEmail()));
						target.addComponent(emailContainer);
					}

					@Override
					protected void onCancel(AjaxRequestTarget target) {
						emailContainer.addOrReplace(
								new Label("emailPanel",getJESession().getUser().getEmail()));
						target.addComponent(emailContainer);
						
					}
					
				};
			}
		};
		load.setOutputMarkupId(true);
		return load;
	}
	
	private AjaxLink getChangePasswordLink(){
		AjaxLink link = new AjaxLink("changePasswordLink"){
			public void onClick(AjaxRequestTarget target) {
				passwordPanel = getPasswordPanel();
				passwordContainer.addOrReplace(passwordPanel);
				target.addComponent(passwordContainer);
			}
		};
		link.setOutputMarkupId(true);
		return link;
	}
	
	private AjaxLink getEditEmailLink(){
		AjaxLink link = new AjaxLink("editEmailLink"){
			public void onClick(AjaxRequestTarget target) {
				emailContainer.addOrReplace(getEmailPanel());
				target.addComponent(emailContainer);
			}
		};
		link.setOutputMarkupId(true);
		return link;
	}
	
}
