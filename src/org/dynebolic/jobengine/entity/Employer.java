package org.dynebolic.jobengine.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@SuppressWarnings("serial")
@Indexed
@Entity
@Table(name="employers")
public class Employer implements IEntity{
	
	@DocumentId
	@Id
	@GeneratedValue
	private Long id;
	
	@Field
	private String name;
	
	@Field
	private String address;
	
	private String email;
	
	private String phone;
	
	private Boolean visibleAddress;
	
	private Boolean visibleEmail;
	
	private Boolean visiblePhone;
	
	private Boolean isMessage;
	
	@OneToOne(targetEntity=User.class)
	private User user;
	
	@ManyToOne(targetEntity=Location.class)
	private Location location;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getVisibleAddress() {
		return visibleAddress;
	}

	public void setVisibleAddress(Boolean visibleAddress) {
		this.visibleAddress = visibleAddress;
	}

	public Boolean getVisibleEmail() {
		return visibleEmail;
	}

	public void setVisibleEmail(Boolean visibleEmail) {
		this.visibleEmail = visibleEmail;
	}

	public Boolean getVisiblePhone() {
		return visiblePhone;
	}

	public void setVisiblePhone(Boolean visiblePhone) {
		this.visiblePhone = visiblePhone;
	}

	/**
	 * @param isMessage the isMessage to set
	 */
	public void setIsMessage(Boolean isMessage) {
		this.isMessage = isMessage;
	}

	/**
	 * @return the isMessage
	 */
	public Boolean getIsMessage() {
		return isMessage;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	
	
}
