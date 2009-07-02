package org.dynebolic.jobengine.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@SuppressWarnings("serial")
@Indexed
@Entity
@Table(name="job_seeker_educations")
public class JobSeekerEducation implements IEntity{
    
    @DocumentId
    @Id
    @GeneratedValue
    private Long id;
    
    @IndexedEmbedded
    @ManyToOne(cascade=CascadeType.MERGE)
    private EducationLevel level;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    private JobSeeker jobSeeker;
    
    @Field
    private String faculty;
    
    @Field
    private String institute;
    
    @Column(length=4,name="startYear")
    private Integer start;
    
    @Column(length=4,name="doneYear")
    private Integer done;
    
    @Field
    private Double ipk;
    
    private boolean last;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getIpk() {
        return ipk;
    }

    public void setIpk(Double ipk) {
        this.ipk = ipk;
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

    /**
     * @param faculty the faculty to set
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     * @return the faculty
     */
    public String getFaculty() {
        return faculty;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getInstitute() {
        return institute;
    }

    public void setLevel(EducationLevel level) {
        this.level = level;
    }

    public EducationLevel getLevel() {
        return level;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getStart() {
        return start;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Integer getDone() {
        return done;
    }

	/**
	 * @param last the last to set
	 */
	public void setLast(boolean last) {
		this.last = last;
	}

	/**
	 * @return the last
	 */
	public boolean isLast() {
		return last;
	}
    
    
    
    
}
