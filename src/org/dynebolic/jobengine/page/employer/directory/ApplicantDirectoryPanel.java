package org.dynebolic.jobengine.page.employer.directory;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.panel.Panel;
import org.dynebolic.jobengine.page.message.MessagePanel;
import org.dynebolic.library.AjaxLazyLoadPanel;

public class ApplicantDirectoryPanel extends Panel{
	ModalWindow message = new ModalWindow("message");
	public ApplicantDirectoryPanel(String id) {
		super(id);
	}

}
