package at.campus02.bp2.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

public class Produktvorschlag implements Serializable {

	private static final long serialVersionUID = -498771268281680756L;

	public Produktvorschlag(){
	}
	
	private Kunde kunde;
	private Standort standort;
	private String produkttyp;
	private List<Produkt> produkte;
	
	public Kunde getKunde(){
		return this.kunde;
	}
	public void setKunde(Kunde kunde){
		System.out.print("setKunde: ");
		System.out.println(kunde.getKundenname());
		this.kunde = kunde;
	}
	
	public Standort getStandort(){
		return this.standort;
	}
	public void setStandort(Standort standort){
		this.standort = standort;
	}
	
	public String getProdukttyp(){
		return this.produkttyp;
	}
	public void setProdukttyp(String produkttyp){
		System.out.print("setProdukttyp: ");
		System.out.println(produkttyp);
		this.produkttyp = produkttyp;
	}
	
	public List<Produkt> getProdukte(){
		return this.produkte;
	}
	public void setProdukte(List<Produkt> produkte){
		this.produkte = produkte;
	}
}
