package org.dynebolic.jobengine.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    public Job(Long id, String summary, String position) {
        this.id = id;
        this.summary = summary;
        this.position = position;
    }



    @Id
    @GeneratedValue
    @DocumentId
    private Long id = null;
    
    @Field
    private String summary = null;
    
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
    private String skills;
    
    @Field
    private String languages;
    
    @Field
    private Integer experience;
    
    @Field
    private String additionalSkills;
    
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
    @ManyToOne(cascade=CascadeType.MERGE)
    private Location location;
    
    @ContainedIn
    @OneToMany(mappedBy="job",cascade=CascadeType.MERGE)
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


    public String getSkills() {
        return skills;
    }


    public void setSkills(String skills) {
        this.skills = skills;
    }


    public String getLanguages() {
        return languages;
    }


    public void setLanguages(String languages) {
        this.languages = languages;
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


    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }


    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
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
    
    public void clear()
    {
        this.id = null;
        this.summary = null;
        this.position = null;
        this.ipk = null;
        this.expired = null;
        this.salary = null;
        this.skills = null;
        this.languages = null;
        this.experience = null;
        this.additionalSkills = null;
        this.description = null;
        this.education = null;
        this.category = null;
        this.employer = null;
        this.location = null;
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
    
    
}
