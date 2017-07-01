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
@Table(name = "KUNDEN") //Gibt an, in welcher Datenbank-Tabelle die Daten gespeichert werden sollen; ich würde die DB-Tabellennamen in der Mehrzahl vergeben
public class Kunde implements Serializable { //Ich würde die Klassen in der Einzahl benennen
	//Diese ID muss für das Serializable Interface erstellt werden
	//Beim kopieren einer Klasse: die Zeile "private static final long serialVersionUID... löschen
	//Cursor über den Klassennamen, "Add generated serialVersionUID"
	private static final long serialVersionUID = -4725518670096451529L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "Kundenname")
	private String kundenname;
	
	@OneToMany(mappedBy = "kunde")
	private Set<Ansprechpartner> ansprechpartner;
	
	@OneToMany(mappedBy = "kunde")
	private Set<Vertrag> vertrag;
	
	@OneToMany(mappedBy = "kunde")
	private Set<Standort> standort;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getKundenname(){
		return kundenname;
	}
	
	public void setKundenname(String kundenname){
		this.kundenname = kundenname;
	}
	
	public Set<Ansprechpartner> getAnsprechpartner(){
		return this.ansprechpartner;
	}
	
	public void setAnsprechpartner(Set<Ansprechpartner> ansprechpartner){
		this.ansprechpartner = ansprechpartner;
	}
	
	public Set<Vertrag> getVertrag()
	{
		return this.vertrag;
	}
	public void setVertrag(Set<Vertrag> vertrag){
		this.vertrag = vertrag;
	}
	
	public Set<Standort> getStandort()
	{
		return this.standort;
	}
	public void setStandort(Set<Standort> standort){
		this.standort = standort;
	}
	
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		final Kunde other = (Kunde) obj;
		if(this.getId() == null && other.getId() == null)
			return true;
		if(this.getId() == null || other.getId() == null)
			return false;
		if(this.getId() == other.getId())
			return true;
		if(this.getId().longValue() == other.getId().longValue())
			return true;
		if(this.getId().equals(other.getId()))
			return true;
		if(this.getId() != other.getId() && (this.getId() != null || !this.getId().equals(other.getId()))){
			return false;
		}
		return true;
	}
}
