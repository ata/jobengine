package org.dynebolic.jobengine;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.dynebolic.jobengine.entity.User;
import org.dynebolic.jobengine.service.UserService;

@SuppressWarnings("serial")
public class JobEngineAuthenticatedWebSession extends AuthenticatedWebSession {
	private UserService service;
	private User user;
	public JobEngineAuthenticatedWebSession(Request request) {
		super(request);
		service = new UserService();
	}

	@Override
	public boolean authenticate(String username, String password) {
		user = service.authenticate(username.toLowerCase(), password.toLowerCase());
		
		if(user != null) {
			return true;
		}
		return false;
	}

	@Override
	public Roles getRoles() {
		if (isSignedIn())
        {			
            return new Roles(user.getRole().getName());
        }
        return new Roles("Guest");
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

}
