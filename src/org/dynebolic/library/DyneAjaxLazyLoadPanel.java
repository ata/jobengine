package org.dynebolic.library;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;

public class DyneAjaxLazyLoadPanel extends AjaxLazyLoadPanel {
	private Component component;
	public DyneAjaxLazyLoadPanel(Component component) {
		super(component.getId());
		this.component = component;
	}

	@Override
	public Component getLazyLoadComponent(String id) {
		return component;
	}

}
