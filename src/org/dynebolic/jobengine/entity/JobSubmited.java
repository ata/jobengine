package org.dynebolic.jobengine.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="job_submiteds")
public class JobSubmited implements IEntity {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private Date dateSubmited = Calendar.getInstance().getTime();
    
    @ManyToOne(cascade=CascadeType.MERGE)
    private JobSeeker jobSeeker;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    private Job job;
    
    private Boolean approved = false;
    
    private Boolean ignore = false;
    
    @Column(length=1000)
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateSubmited() {
        return dateSubmited;
    }

    public void setDateSubmited(Date dateSubmited) {
        this.dateSubmited = dateSubmited;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
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

    public void setJob(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setIgnore(Boolean ignore) {
        this.ignore = ignore;
    }

    public Boolean getIgnore() {
        return ignore;
    }
    
    
    
    
}
