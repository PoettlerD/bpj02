package at.campus02.bp2.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Ich habe die Bezeichnung auf Deutsch nun verwendet, da wir es damit leichter von den "Beispielen" aus der Demo von den Vortragenden außeinander halten können
@Entity
@Table(name = "KUNDENSEGMENT") //Gibt an, in welcher Datenbank-Tabelle die Daten gespeichert werden sollen; ich würde die DB-Tabellennamen in der Mehrzahl vergeben
public class Kundensegment implements Serializable { 

	//Ich würde die Klassen in der Einzahl benennen
		//Diese ID muss für das Serializable Interface erstellt werden
		//Beim kopieren einer Klasse: die Zeile "private static final long serialVersionUID... löschen
		//Cursor über den Klassennamen, "Add generated serialVersionUID"
		//das serialVersionUID  muss änderen, habe versucht aber geht nichts

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2605995093854598889L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "Name")
	private String name;
	
	@Column(name = "Beschreibung")
	private String beschreibung;

	

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



	public String getBeschreibung() {
		return beschreibung;
	}



	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kundensegment other = (Kundensegment) obj;
		if (beschreibung == null) {
			if (other.beschreibung != null)
				return false;
		} else if (!beschreibung.equals(other.beschreibung))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
