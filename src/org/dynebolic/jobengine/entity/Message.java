package org.dynebolic.jobengine.entity;

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
@Table(name="messages")
public class Message implements IEntity {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String subject;
    
    @Column(length=2000)
    private String body;
    

    @ManyToOne(cascade=CascadeType.MERGE)
    private User messageFrom;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    private User messageTo;
    
    private Date messageDate;
    
    private Boolean readed = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
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

    public void setMessageFrom(User messageFrom) {
        this.messageFrom = messageFrom;
    }

    public User getMessageFrom() {
        return messageFrom;
    }

    public void setMessageTo(User messageTo) {
        this.messageTo = messageTo;
    }

    public User getMessageTo() {
        return messageTo;
    }

    /**
     * @param readed the readed to set
     */
    public void setReaded(Boolean readed) {
        this.readed = readed;
    }

    /**
     * @return the readed
     */
    public Boolean getReaded() {
        return readed;
    }
    
    

}
