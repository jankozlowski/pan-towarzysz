package com.TheLongestJourney;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name="journey")
public class Journey implements Serializable{
	
	@Id
	@GenericGenerator(name="keygenerator" , strategy="increment")
	@GeneratedValue(generator="keygenerator")
	@Column(name = "id_journey", unique = true, nullable = false)
	private int id;
	private String nazwa;
	private String zdjecie;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime data;
	private String opis;
	private String trasa;
	private String trudnosc;
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime czas;
	private String sprzet;
	private String organizator;
	private int ludzie;
	private String koszt;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime stempel;
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="journey")
	private Set<JourneyPhotos> journeyPhotos = new HashSet<JourneyPhotos>(0);
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="sport_id")
	private Sports sports;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Place placeObject;
	
	
	
	
	public DateTime getData() {
		return data;
	}

	public void setData(DateTime data) {
		this.data = data;
	}

	public DateTime getStempel() {
		return stempel;
	}

	public void setStempel(DateTime stempel) {
		this.stempel = stempel;
	}

	public String getZdjecie() {
		return zdjecie;
		
	}
	
	public void setZdjecie(String zdjecie) {
		this.zdjecie = zdjecie;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getTrasa() {
		return trasa;
	}
	public void setTrasa(String trasa) {
		this.trasa = trasa;
	}
	public String getTrudnosc() {
		return trudnosc;
	}
	public void setTrudnosc(String trudnosc) {
		this.trudnosc = trudnosc;
	}
	public DateTime getCzas() {
		return czas;
	}
	public void setCzas(DateTime czas) {
		this.czas = czas;
	}
	public String getSprzet() {
		return sprzet;
	}
	public void setSprzet(String sprzet) {
		this.sprzet = sprzet;
	}
	public String getOrganizator() {
		return organizator;
	}
	public void setOrganizator(String organizator) {
		this.organizator = organizator;
	}
	public String getKoszt() {
		return koszt;
	}
	public void setKoszt(String koszt) {
		this.koszt = koszt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Set<JourneyPhotos> getJourneyPhotos() {
		return journeyPhotos;
	}

	public void setJourneyPhotos(Set<JourneyPhotos> journeyPhotos) {
		this.journeyPhotos = journeyPhotos;
	}

	public Place getPlaceObject() {
		return placeObject;
	}

	public void setPlaceObject(Place placeObject) {
		this.placeObject = placeObject;
	}

	public int getLudzie() {
		return ludzie;
	}

	public void setLudzie(int ludzie) {
		this.ludzie = ludzie;
	}

	public Sports getSports() {
		return sports;
	}

	public void setSports(Sports sports) {
		this.sports = sports;
	}


}

/*
 * create table journey
(
    id_journey integer AUTO_INCREMENT,
    sport_id integer,
    nazwa varchar(64),
    organizator varchar(48),
    opis text,
    data datetime,
    koszt text,
    ludzie integer,
    zdjecie varchar(200),
    trasa text,
    trudnosc varchar(48),
    czas datetime,
    sprzet text,
    stempel timestamp,
    CONSTRAINT pk_id_journey PRIMARY KEY (id_journey)
)
*/
