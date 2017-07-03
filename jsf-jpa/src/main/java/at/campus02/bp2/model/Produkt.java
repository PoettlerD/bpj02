package at.campus02.bp2.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUKTE")
public class Produkt implements Serializable {

	private static final long serialVersionUID = -4136968125477770039L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "Produktname")
	private String produktname;

	@Column(name = "Beschreibung")
	private String beschreibung;

	@Column(name = "Preis")
	private int preis;
	
	@Column(name = "Produkttyp")
	private String produkttyp;

	@Column(name = "Technologie")
	private String technologie;
	
	@Column(name = "AnzahlderGespraeche")
	private int anzahlderGespraeche;
	
	@Column(name = "Bandbreite")
	private String bandbreite;
	
	@Column(name = "Leitungstyp")
	private String leitungstyp;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduktname() {
		return this.produktname;
	}

	public void setProduktname(String produktname){
		this.produktname = produktname;
	}
	
	public int getPreis() {
		return this.preis;
	}
	
	public void setPreis(int preis) {
		this.preis = preis;
	}
	
	public String getProdukttyp() {
		return this.produkttyp;
	}
	
	public void setProdukttyp(String produkttyp) {
		this.produkttyp = produkttyp;
	}

	public String getTechnologie() {
		return this.technologie;
	}
	
	public void setTechnologie(String technologie) {
		this.technologie = technologie;
	}
	
	public int getAnzahlderGespraeche() {
		return this.anzahlderGespraeche;
	}
	
	public void setAnzahlderGespraeche(int anzahlderGespraeche) {
		this.anzahlderGespraeche = anzahlderGespraeche;
	}
	
	public String getBandbreite() {
		return this.bandbreite;
	}
	
	public void setBandbreite(String bandbreite) {
		this.bandbreite = bandbreite;
	}
	
	public String getLeitungstyp() {
		return this.leitungstyp;
	}
	
	public void setLeitungstyp(String leitungstyp) {
		this.leitungstyp = leitungstyp;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	
}
