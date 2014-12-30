package com.TheLongestJourney;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="journeyPhotos")
public class JourneyPhotos implements java.io.Serializable{

	@Id
	@GenericGenerator(name="keygenerator" , strategy="increment")
	@GeneratedValue(generator="keygenerator")
	@Column(name = "photoId", unique = true, nullable = false)
	private long id;
	@Column(name = "photoName")
	private String photo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_journey", nullable = false)
	private Journey journey;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Journey getJourney() {
		return journey;
	}
	public void setJourney(Journey journey) {
		this.journey = journey;
	}



	
	
	
}

/*
 * create table journeyPhotos
(
	photoId serial,
    id_journey integer,
    photoName text,
    PRIMARY KEY (photoId),
	FOREIGN KEY (id_journey) REFERENCES journey(id_journey) on update cascade on delete cascade
)
*/

