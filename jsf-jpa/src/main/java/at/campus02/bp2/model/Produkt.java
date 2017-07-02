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

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "Produktname")
	private String produktname;

	@Column(name = "Beschreibung")
	private String beschreibung;

	@Column(name = "Preis")
	private Integer preis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduktnamee() {
		return this.produktname;
	}

	public void setProduktname(String produktname){
		this.produktname = produktname;
	}
	
	public Integer getPreis() {
		return this.preis;
	}
	
	public void setPreis(Integer preis) {
		this.preis = preis;
	}
	

}
