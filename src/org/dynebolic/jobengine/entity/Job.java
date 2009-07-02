package org.dynebolic.jobengine.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;


@SuppressWarnings("serial")
@Entity
@Indexed
@Table(name="jobs")
public class Job implements IEntity{
    
    public Job(){
        
    }


    @Id
    @GeneratedValue
    @DocumentId
    private Long id = null;
    
    @Field
    private String title = null;
    
    @Field
    private String position = null;
    
    @Field
    private Double ipk;
    
    @Field
    private Date expired;
    
    @Field
    private Date postedDate = Calendar.getInstance().getTime();
    
    @Field
    private Long salary;
    
    @Field
    private Integer experience;
    
    @Field
    private String additionalSkills;
    
    @Field
    @Column(length = 1000)
    private String additionalRequirements;
    
    @Field
    @Column(length = 1000)
    private String description;
    
    @IndexedEmbedded
    @ManyToOne(cascade=CascadeType.MERGE)
    private EducationLevel education;
    
    @IndexedEmbedded
    @ManyToOne(cascade=CascadeType.MERGE)
    private JobCategory category;
    
    @IndexedEmbedded
    @ManyToOne(cascade=CascadeType.MERGE)
    private Employer employer;
    
    @IndexedEmbedded
    @ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
    private Country country;
    
    @IndexedEmbedded
    @ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
    private Location location;
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="job")
    private List<JobSkill> skillRequirements;
    
    @ContainedIn
    @OneToMany(mappedBy="job",cascade=CascadeType.ALL)
    private List<JobLanguage> languages;
    
    @ContainedIn
    @OneToMany(mappedBy="job",cascade=CascadeType.ALL)
    private List<JobSubmited> submiteds;
    

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getPosition() {
        return position;
    }


    public void setPosition(String position) {
        this.position = position;
    }


    public Double getIpk() {
        return ipk;
    }


    public void setIpk(Double ipk) {
        this.ipk = ipk;
    }


    public Date getExpired() {
        return expired;
    }


    public void setExpired(Date expired) {
        this.expired = expired;
    }


    public Long getSalary() {
        return salary;
    }


    public void setSalary(Long salary) {
        this.salary = salary;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public JobCategory getCategory() {
        return category;
    }


    public void setCategory(JobCategory category) {
        this.category = category;
    }


    public Employer getEmployer() {
        return employer;
    }


    public void setEmployer(Employer employer) {
        this.employer = employer;
    }


    public Location getLocation() {
        return location;
    }


    public void setLocation(Location location) {
        this.location = location;
    }


    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        
    }


    public void setAdditionalSkills(String additionalSkills) {
        this.additionalSkills = additionalSkills;
    }

    public String getAdditionalSkills() {
        return additionalSkills;
    }



    /**
     * @param experience the experience to set
     */
    public void setExperience(Integer experience) {
        this.experience = experience;
    }



    /**
     * @return the experience
     */
    public Integer getExperience() {
        return experience;
    }


    /**
     * @param postedDate the postedDate to set
     */
    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }



    /**
     * @return the postedDate
     */
    public Date getPostedDate() {
        return postedDate;
    }
	/**
	 * @param education the education to set
	 */
	public void setEducation(EducationLevel education) {
		this.education = education;
	}
	/**
	 * @return the education
	 */
	public EducationLevel getEducation() {
		return education;
	}
	/**
	 * @param submiteds the submiteds to set
	 */
	public void setSubmiteds(List<JobSubmited> submiteds) {
		this.submiteds = submiteds;
	}
	/**
	 * @return the submiteds
	 */
	public List<JobSubmited> getSubmiteds() {
		return submiteds;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param additionalRequirements the additionalRequirements to set
	 */
	public void setAdditionalRequirements(String additionalRequirements) {
		this.additionalRequirements = additionalRequirements;
	}
	/**
	 * @return the additionalRequirements
	 */
	public String getAdditionalRequirements() {
		return additionalRequirements;
	}
	public void setSkillRequirements(List<JobSkill> skillRequirements) {
		this.skillRequirements = skillRequirements;
	}
	public List<JobSkill> getSkillRequirements() {
		return skillRequirements;
	}


	public void setLanguages(List<JobLanguage> languages) {
		this.languages = languages;
	}


	public List<JobLanguage> getLanguages() {
		return languages;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public Country getCountry() {
		return country;
	}
    
    
}
