package com.TheLongestJourney;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;


@Entity
@Table(name="users", 
uniqueConstraints = {
@UniqueConstraint(columnNames = "user"),
@UniqueConstraint(columnNames = "email") 
})

public class User implements Serializable{

	@Id
	@GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="userDataObject"))
	@GeneratedValue(generator="gen")
	private Long user_id;
	
	@Column(name = "user", unique = true, nullable = false)
	private String user;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private UserData userDataObject;
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime stempel;
	
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserData getUserDataObject() {
		return userDataObject;
	}
	public void setUserDataObject(UserData userDataObject) {
		this.userDataObject = userDataObject;
	}
	public DateTime getStempel() {
		return stempel;
	}
	public void setStempel(DateTime stempel) {
		this.stempel = stempel;
	}
	
	
	
}



/*
create table users(

user_id integer AUTO_INCREMENT,
user varchar(64) NOT NULL UNIQUE,
email varchar(255) NOT NULL UNIQUE ,
password varchar(64) NOT NULL,
stempel timestamp,
PRIMARY KEY (user_id)

);*/