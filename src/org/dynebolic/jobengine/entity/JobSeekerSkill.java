package org.dynebolic.jobengine.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@SuppressWarnings("serial")
@Indexed
@Entity
@Table(name="job_seeker_skills")
public class JobSeekerSkill implements IEntity{  
    
    @DocumentId
    @Id
    @GeneratedValue
    private Long id;
    
    @Field
    private String name;
    
    @Field
    private String detail;
    
    @Field
    private Integer experience;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    private JobSeeker jobSeeker;
    
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


    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
    
    
    
    
}
