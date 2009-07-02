package org.dynebolic.jobengine.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

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
    @Column(length=2000)
    private String description;
    
    private String photo;
    
    @Field
    private String address;
    
    private String email;
    
    private String phone;
    
    private String website;
    
    private Boolean visibleAddress;
    
    private Boolean visibleEmail;
    
    private Boolean visiblePhone;
    
    private Boolean isMessage;
    
    @OneToOne(cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
    private User user;
    
    @IndexedEmbedded
    @ManyToOne(cascade=CascadeType.MERGE)
    private Location location;
    
    @IndexedEmbedded
    @ManyToOne(cascade=CascadeType.MERGE)
    private Country country;
    
    @IndexedEmbedded
    @ManyToOne(cascade=CascadeType.MERGE)
    private JobCategory category;
    
    @OneToMany(cascade=CascadeType.MERGE,mappedBy="employer")
    private List<Job> jobs;
    
    @ManyToMany(cascade=CascadeType.MERGE)
    private Set<JobSeeker> bookmarks = new HashSet<JobSeeker>();
    
    public Employer() {
    }
    public Employer(String name) {
        this.name = name;
    }
    
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
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public JobCategory getCategory() {
		return category;
	}
	public void setCategory(JobCategory category) {
		this.category = category;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	public List<Job> getJobs() {
		return jobs;
	}
	public void setBookmarks(Set<JobSeeker> bookmarks) {
		this.bookmarks = bookmarks;
	}
	public Set<JobSeeker> getBookmarks() {
		return bookmarks;
	}
    
    
    
}
