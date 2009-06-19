package org.dynebolic.jobengine.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@SuppressWarnings("serial")
@Indexed
@Entity
@Table(name="job_seekers")
public class JobSeeker implements IEntity{
	
	@DocumentId
	@Id
	@GeneratedValue
	private Long id;
	
	@Field
	private String summary;
	
	@Field
	private String name;
	
	private Date birthDate;
	
	private String birthPlace;
	
	private String address;
	
	private String cellular;
	
	@Field
	private String gender;
	
	@Field
	private String keySkills;
	
	@Field
	@Column(length=1000)
	private String description;
	
	private String phone;
	
	private String personalWeb;

	@ContainedIn
	@OneToMany(targetEntity=JobSeekerSkill.class)
	private List<JobSeekerSkill> skills;
	
	@OneToOne(targetEntity=User.class)
	private User user;
	
	private MediaAsset photo;
	
	private MediaAsset thumbnail;
	
	
	@IndexedEmbedded
	@ManyToOne(targetEntity=Location.class)
	private Location currentLocation;
	
	@IndexedEmbedded
	@ManyToOne
	private Country country;
	
	@IndexedEmbedded
	@OneToOne(targetEntity=JobSeekerEducation.class)
	private JobSeekerEducation lastEducation;
	
	
	@ContainedIn
	@OneToMany(targetEntity=JobSeekerEducation.class)
	private List<JobSeekerEducation> educations;
	
	@ContainedIn
	@OneToMany(targetEntity=JobSeekerExperience.class)
	private List<JobSeekerExperience> experiences;
	
	@ContainedIn
	@OneToMany(targetEntity=JobSeekerCertificate.class)
	private List<JobSeekerCertificate> certificates;
	
	@ContainedIn
	@OneToMany(targetEntity=Language.class)
	private List<Language> languages;
	
	@ContainedIn
	@ManyToMany(targetEntity=Location.class)
	private List<Location> preferLocations;

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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCellular() {
		return cellular;
	}

	public void setCellular(String cellular) {
		this.cellular = cellular;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPersonalWeb() {
		return personalWeb;
	}

	public void setPersonalWeb(String personalWeb) {
		this.personalWeb = personalWeb;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public List<Location> getPreferLocations() {
		return preferLocations;
	}

	public void setPreferLocations(List<Location> preferLocations) {
		this.preferLocations = preferLocations;
	}

	public List<JobSeekerEducation> getEducations() {
		return educations;
	}

	public void setEducations(List<JobSeekerEducation> educations) {
		this.educations = educations;
	}

	public List<JobSeekerExperience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<JobSeekerExperience> experiences) {
		this.experiences = experiences;
	}

	public List<JobSeekerCertificate> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<JobSeekerCertificate> certificates) {
		this.certificates = certificates;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(List<JobSeekerSkill> skills) {
		this.skills = skills;
	}

	/**
	 * @return the skills
	 */
	public List<JobSeekerSkill> getSkills() {
		return skills;
	}

	/**
	 * @param lastEducation the lastEducation to set
	 */
	public void setLastEducation(JobSeekerEducation lastEducation) {
		this.lastEducation = lastEducation;
	}

	/**
	 * @return the lastEducation
	 */
	public JobSeekerEducation getLastEducation() {
		return lastEducation;
	}

	/**
	 * @param keySkills the keySkills to set
	 */
	public void setKeySkills(String keySkills) {
		this.keySkills = keySkills;
	}

	/**
	 * @return the keySkills
	 */
	public String getKeySkills() {
		return keySkills;
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

	/**
	 * @param languages the languages to set
	 */
	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	/**
	 * @return the languages
	 */
	public List<Language> getLanguages() {
		return languages;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param thumbnail the thumbnail to set
	 */
	public void setThumbnail(MediaAsset thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * @return the thumbnail
	 */
	public MediaAsset getThumbnail() {
		return thumbnail;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(MediaAsset photo) {
		this.photo = photo;
	}

	/**
	 * @return the photo
	 */
	public MediaAsset getPhoto() {
		return photo;
	}
	
	
	


}
