package com.TheLongestJourney;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="UserData")
public class UserData implements Serializable{
	
	@Id
	@GenericGenerator(name="keygenerator" , strategy="increment")
	@GeneratedValue(generator="keygenerator")
	@Column(insertable =  false, updatable = false)
	private Long user_id;


	private String user;
	private String email;
	private String name;
	private String surname;
	private boolean gender;
	private int age;
	private String photo;
	private String about;
	private String phone;
	
	
	@OneToOne(mappedBy="userDataObject", cascade=CascadeType.ALL)
	private User userObject;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sport_id")
	private Sports sport;


	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public boolean getGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Sports getSport() {
		return sport;
	}
	public void setSport(Sports sport) {
		this.sport = sport;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public User getUserObject() {
		return userObject;
	}
	public void setUserObject(User userObject) {
		this.userObject = userObject;
	}

	
}


/*
CREATE TABLE UserData(
	user_id integer NOT NULL UNIQUE,
	sport_id integer UNIQUE,
	user varchar(64) NOT NULL UNIQUE,
	email varchar(255) NOT NULL UNIQUE,
	name varchar(32),
	surname varchar(48),
	gender boolean,
	age integer,
	photo varchar(64),
	about text,
	phone varchar(24),
	PRIMARY KEY (user_id),
	FOREIGN KEY (user_id) REFERENCES users(user_id) on update cascade on delete cascade,
	FOREIGN KEY (sport_id) REFERENCES sports(sport_id) on update cascade on delete cascade
	
)



*/