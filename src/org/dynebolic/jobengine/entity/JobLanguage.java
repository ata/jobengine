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
@Table(name="job_languages")
public class JobLanguage implements IEntity {
    
    @DocumentId
    @Id
    @GeneratedValue
    private Long id;
    
    @IndexedEmbedded
    @ManyToOne
    private Language language;
    
    @Field
    @Column(name="spokenLevel")
    private String level;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    private Job job;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
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

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

	public void setJob(Job job) {
		this.job = job;
	}

	public Job getJob() {
		return job;
	}
    
}
