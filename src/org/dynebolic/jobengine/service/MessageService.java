package org.dynebolic.jobengine.service;

import org.dynebolic.jobengine.entity.Message;

@SuppressWarnings("serial")
public class MessageService extends GenericService<Message> {
	public MessageService() {
		super(Message.class);
	}

}
