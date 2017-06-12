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
@Table(name = "PERSONEN")
public class Person implements Serializable {

	private static final long serialVersionUID = -4136968137287770039L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "Vorname")
	private String vorname;

	@Column(name = "Nachname")
	private String nachname;

	@Column(name = "Telefonnummer")
	private String telefonnummer;

	@Column(name = "Mobiltelefonnummer")
	private String mobiltelefonnummer;

	@Column(name = "Emailadresse")
	private String emailadresse;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname){
		this.vorname = vorname;
	}
	
	public void setStreet(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return this.nachname;
	}
	
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	

	public String getName(){
		return this.vorname + " " + this.nachname;
	}

	public String getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	public String getMobiltelefonnummer() {
		return this.mobiltelefonnummer;
	}

	public void setMobiltelefonnummer(String mobiltelefonnummer) {
		this.mobiltelefonnummer = mobiltelefonnummer;
	}

	public String getEmailadresse() {
		return this.emailadresse;
	}

	public void setEmailadresse(String emailadresse) {
		this.emailadresse = emailadresse;
	}

}
