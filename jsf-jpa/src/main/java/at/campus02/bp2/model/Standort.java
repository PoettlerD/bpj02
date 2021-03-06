package at.campus02.bp2.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Standorte")
public class Standort implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 6535422812028223012L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="KundeID", nullable=false)
	private Kunde kunde;

	@Column(name="Standorttyp")
	private String standorttyp;
	
	@Column(name="Adresse")
	private String adresse;
	
	@Column(name="Detail")
	private String detail;
	
	@Column(name="PLZ")
	private int plz;
	
	@Column(name="Ort")
	private String ort;

	@Column(name="Land")
	private String land;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public String getStandorttyp() {
		return standorttyp;
	}

	public void setStandorttyp(String standorttyp) {
		this.standorttyp = standorttyp;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}
	
	public String getName(){
		return this.standorttyp + " " + this.adresse + ", " + this.plz + " " + this.ort;
	}
	
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		final Standort other = (Standort) obj;
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
