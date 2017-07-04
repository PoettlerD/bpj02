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
@Table(name = "FEEDBACK")
public class Feedback implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7601787518252911532L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name="Datum")
	private Date datum;
	
	@Column(name="Bewertung")
	private int bewertung;
	
	@Column(name="Inhalt")
	private String Inhalt;
	
	@ManyToOne
	@JoinColumn(name="VertragID", nullable=false)
	private Vertrag vertrag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getBewertung() {
		return bewertung;
	}

	public void setBewertung(int bewertung) {
		this.bewertung = bewertung;
	}

	public String getInhalt() {
		return Inhalt;
	}

	public void setInhalt(String inhalt) {
		Inhalt = inhalt;
	}

	public Vertrag getVertrag() {
		return vertrag;
	}

	public void setVertrag(Vertrag vertrag) {
		this.vertrag = vertrag;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		final Feedback other = (Feedback) obj;
		if(this.getId() == other.getId())
			return true;
		if(this.getId() == null && other.getId() == null)
			return true;
		if(this.getId() == null || other.getId() == null)
			return false;
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
