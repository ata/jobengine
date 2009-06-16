package org.dynebolic.jobengine.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements IEntity{
	private static final long serialVersionUID = 4523097555016898123L;
	
	@Id
    @GeneratedValue
    private Long id;
    
	@Column(unique=true)
    private String username;
    
	@Column(unique=true)
    private String email;
    
    private String password;
    
    private String name;
    
    private Boolean complete = false;
    
    @OneToMany(mappedBy="messageTo")
    private List<Message> inboxMessages;
    
    @OneToMany(mappedBy="messageFrom")
    private List<Message> sendMessage;
    
    @ManyToOne(targetEntity=Role.class)
    private Role role;
    
    @OneToOne(mappedBy="user")
    private Employer employer;
    
    @OneToOne(mappedBy="user")
    private JobSeeker jobSeeker;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password =  password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	/**
	 * @param employer the employer to set
	 */
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	/**
	 * @return the employer
	 */
	public Employer getEmployer() {
		return employer;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	/**
	 * @param jobSeeker the jobSeeker to set
	 */
	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	/**
	 * @return the jobSeeker
	 */
	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	/**
	 * @param inboxMessages the inboxMessages to set
	 */
	public void setInboxMessages(List<Message> inboxMessages) {
		this.inboxMessages = inboxMessages;
	}

	/**
	 * @return the inboxMessages
	 */
	public List<Message> getInboxMessages() {
		return inboxMessages;
	}

	/**
	 * @param sendMessage the sendMessage to set
	 */
	public void setSendMessage(List<Message> sendMessage) {
		this.sendMessage = sendMessage;
	}

	/**
	 * @return the sendMessage
	 */
	public List<Message> getSendMessage() {
		return sendMessage;
	}

	/**
	 * @param complete the complete to set
	 */
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	/**
	 * @return the complete
	 */
	public Boolean getComplete() {
		return complete;
	}
    
    
    
    
    
}
