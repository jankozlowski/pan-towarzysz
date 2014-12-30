package com.TheLongestJourney;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sports", 
uniqueConstraints = {
@UniqueConstraint(columnNames = "sport_id")
})
public class Sports {

	@Id
	@GenericGenerator(name="keygenerator" , strategy="increment")
	@GeneratedValue(generator="keygenerator")
	private Long sport_id;
	
	@OneToOne(mappedBy="sport", cascade=CascadeType.ALL)
	private UserData userData;
	
	@OneToOne(mappedBy="sports", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Journey journey;
	
	private boolean bieganie;
	private boolean jazda_konna;
	private boolean lucznictwo;
	private boolean babington;
	private boolean baseball;
	private boolean koszykowka;
	private boolean jazda_rowerem;
	private boolean kajakarstwo;
	private boolean wspinaczka;
	private boolean taniec;
	private boolean nurkowanie;
	private boolean szermierka;
	private boolean wedkarstwo;
	private boolean pilka_nozna;
	private boolean geocaching;
	private boolean golf;
	private boolean hokey;
	private boolean lyzwiarstwo;
	private boolean sztuki_walki;
	private boolean medytacja;
	private boolean spadochroniarstwo;
	private boolean polo;
	private boolean wioslarstwo;
	private boolean strzelectwo;
	private boolean skateboarding;
	private boolean rolkarstwo;
	private boolean narciarstwo;
	private boolean snowboarding;
	private boolean surfing;
	private boolean plywanie;
	private boolean tennis;
	private boolean trekking;
	private boolean volleyball;
	private boolean chodzenie;
	private boolean podnoszenie;
	private boolean zeglarstwo;

	public boolean isBieganie() {
		return bieganie;
	}
	public void setBieganie(boolean bieganie) {
		this.bieganie = bieganie;
	}
	public boolean isJazda_konna() {
		return jazda_konna;
	}
	public void setJazda_konna(boolean jazda_konna) {
		this.jazda_konna = jazda_konna;
	}
	public boolean isLucznictwo() {
		return lucznictwo;
	}
	public void setLucznictwo(boolean lucznictwo) {
		this.lucznictwo = lucznictwo;
	}
	public boolean isBabington() {
		return babington;
	}
	public void setBabington(boolean babington) {
		this.babington = babington;
	}
	public boolean isBaseball() {
		return baseball;
	}
	public void setBaseball(boolean baseball) {
		this.baseball = baseball;
	}
	public boolean isKoszykowka() {
		return koszykowka;
	}
	public void setKoszykowka(boolean koszykowka) {
		this.koszykowka = koszykowka;
	}
	public boolean isJazda_rowerem() {
		return jazda_rowerem;
	}
	public void setJazda_rowerem(boolean jazda_rowerem) {
		this.jazda_rowerem = jazda_rowerem;
	}
	public boolean isKajakarstwo() {
		return kajakarstwo;
	}
	public void setKajakarstwo(boolean kajakarstwo) {
		this.kajakarstwo = kajakarstwo;
	}
	public boolean isWspinaczka() {
		return wspinaczka;
	}
	public void setWspinaczka(boolean wspinaczka) {
		this.wspinaczka = wspinaczka;
	}
	public boolean isTaniec() {
		return taniec;
	}
	public void setTaniec(boolean taniec) {
		this.taniec = taniec;
	}
	public boolean isNurkowanie() {
		return nurkowanie;
	}
	public void setNurkowanie(boolean nurkowanie) {
		this.nurkowanie = nurkowanie;
	}
	public boolean isSzermierka() {
		return szermierka;
	}
	public void setSzermierka(boolean szermierka) {
		this.szermierka = szermierka;
	}
	public boolean isWedkarstwo() {
		return wedkarstwo;
	}
	public void setWedkarstwo(boolean wedkarstwo) {
		this.wedkarstwo = wedkarstwo;
	}
	public boolean isPilka_nozna() {
		return pilka_nozna;
	}
	public void setPilka_nozna(boolean pilka_nozna) {
		this.pilka_nozna = pilka_nozna;
	}
	public boolean isGeocaching() {
		return geocaching;
	}
	public void setGeocaching(boolean geocaching) {
		this.geocaching = geocaching;
	}
	public boolean isGolf() {
		return golf;
	}
	public void setGolf(boolean golf) {
		this.golf = golf;
	}
	public boolean isHokey() {
		return hokey;
	}
	public void setHokey(boolean hokey) {
		this.hokey = hokey;
	}
	public boolean isLyzwiarstwo() {
		return lyzwiarstwo;
	}
	public void setLyzwiarstwo(boolean lyzwiarstwo) {
		this.lyzwiarstwo = lyzwiarstwo;
	}
	public boolean isSztuki_walki() {
		return sztuki_walki;
	}
	public void setSztuki_walki(boolean sztuki_walki) {
		this.sztuki_walki = sztuki_walki;
	}
	public boolean isMedytacja() {
		return medytacja;
	}
	public void setMedytacja(boolean medytacja) {
		this.medytacja = medytacja;
	}
	public boolean isSpadochroniarstwo() {
		return spadochroniarstwo;
	}
	public void setSpadochroniarstwo(boolean spadochroniarstwo) {
		this.spadochroniarstwo = spadochroniarstwo;
	}
	public boolean isPolo() {
		return polo;
	}
	public void setPolo(boolean polo) {
		this.polo = polo;
	}
	public boolean isWioslarstwo() {
		return wioslarstwo;
	}
	public void setWioslarstwo(boolean wioslarstwo) {
		this.wioslarstwo = wioslarstwo;
	}
	public boolean isStrzelectwo() {
		return strzelectwo;
	}
	public void setStrzelectwo(boolean strzelectwo) {
		this.strzelectwo = strzelectwo;
	}
	public boolean isSkateboarding() {
		return skateboarding;
	}
	public void setSkateboarding(boolean skateboarding) {
		this.skateboarding = skateboarding;
	}
	public boolean isRolkarstwo() {
		return rolkarstwo;
	}
	public void setRolkarstwo(boolean rolkarstwo) {
		this.rolkarstwo = rolkarstwo;
	}
	public boolean isNarciarstwo() {
		return narciarstwo;
	}
	public void setNarciarstwo(boolean narciarstwo) {
		this.narciarstwo = narciarstwo;
	}
	public boolean isSnowboarding() {
		return snowboarding;
	}
	public void setSnowboarding(boolean snowboarding) {
		this.snowboarding = snowboarding;
	}
	public boolean isSurfing() {
		return surfing;
	}
	public void setSurfing(boolean surfing) {
		this.surfing = surfing;
	}
	public boolean isPlywanie() {
		return plywanie;
	}
	public void setPlywanie(boolean plywanie) {
		this.plywanie = plywanie;
	}
	public boolean isTennis() {
		return tennis;
	}
	public void setTennis(boolean tennis) {
		this.tennis = tennis;
	}
	public boolean isTrekking() {
		return trekking;
	}
	public void setTrekking(boolean trekking) {
		this.trekking = trekking;
	}
	public boolean isVolleyball() {
		return volleyball;
	}
	public void setVolleyball(boolean volleyball) {
		this.volleyball = volleyball;
	}
	public boolean isChodzenie() {
		return chodzenie;
	}
	public void setChodzenie(boolean chodzenie) {
		this.chodzenie = chodzenie;
	}
	public boolean isPodnoszenie() {
		return podnoszenie;
	}
	public void setPodnoszenie(boolean podnoszenie) {
		this.podnoszenie = podnoszenie;
	}
	public boolean isZeglarstwo() {
		return zeglarstwo;
	}
	public void setZeglarstwo(boolean zeglarstwo) {
		this.zeglarstwo = zeglarstwo;
	}
	public Long getSport_id() {
		return sport_id;
	}
	public void setSport_id(Long sport_id) {
		this.sport_id = sport_id;
	}
	
	public Journey getJourney() {
		return journey;
	}
	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	
	
}

/*

create table sports(

sport_id integer AUTO_INCREMENT,
bieganie boolean,
jazda_konna boolean,
lucznictwo boolean,
babington boolean,
baseball boolean,
koszykowka boolean,
jazda_rowerem boolean,
kajakarstwo boolean,
wspinaczka boolean,
taniec boolean,
nurkowanie boolean,
szermierka boolean,
wedkarstwo boolean,
pilka_nozna boolean,
geocaching boolean,
golf boolean,
hokey boolean,
lyzwiarstwo boolean,
sztuki_walki boolean,
medytacja boolean,
spadochroniarstwo boolean,
polo boolean,
wioslarstwo boolean,
strzelectwo boolean,
skateboarding boolean,
rolkarstwo boolean,
narciarstwo boolean,
snowboarding boolean,
surfing boolean,
plywanie boolean,
tennis boolean,
trekking boolean,
volleyball boolean,
chodzenie boolean,
podnoszenie boolean,
zeglarstwo boolean,
PRIMARY KEY (sport_id)

)

*/