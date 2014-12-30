package com.TheLongestJourney;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name="newsletter")
public class Newsletter implements Serializable {
	
	private int id;
	private String email;
	private DateTime stempel;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DateTime getStempel() {
		return stempel;
	}

	public void setStempel(DateTime stempel) {
		this.stempel = stempel;
	}
	

}
/*
create table newsletter(
news_id integer NOT NULL AUTO_INCREMENT,
email varchar(255) NOT NULL UNIQUE,
stempel timestamp,
PRIMARY KEY (news_id)

);*/
