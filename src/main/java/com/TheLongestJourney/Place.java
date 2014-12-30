package com.TheLongestJourney;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="place")
public class Place implements Serializable {
	
	@Id
	@GenericGenerator(name="keygenerator" , strategy="increment")
	@GeneratedValue(generator="keygenerator")
	@Column(name = "id_journey", unique = true, nullable = false)
	private int id;
	private double latitude;
	private double longitude;
	private String place;
	

	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longiude) {
		this.longitude = longiude;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	

}

/*create table place(
  id_journey integer,
  latitude double,
  longitude double,
  place varchar(200),
  primary key (id_journey),
  foreign key (id_journey) references journey(id_journey) on update cascade on delete cascade
 );*/
