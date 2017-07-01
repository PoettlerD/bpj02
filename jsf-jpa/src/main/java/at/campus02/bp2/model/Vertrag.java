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
@Table(name = "Vertraege")
public class Vertrag implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3892830040538716104L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name="Vertragsnummer")
	private int vertragsnummer;

	@Column(name="Vertragsbeginn")
	private Date vertragsbeginn;
	
	@Column(name="Vertragsende")
	private Date vertragsende;

	@ManyToOne
	@JoinColumn(name="KundeID", nullable=false)
	private Kunde kunde;
	
	@OneToMany(mappedBy = "vertrag")
	private Set<Feedback> feedback;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVertragsnummer() {
		return vertragsnummer;
	}

	public void setVertragsnummer(int vertragsnummer) {
		this.vertragsnummer = vertragsnummer;
	}

	public Date getVertragsbeginn() {
		return vertragsbeginn;
	}

	public void setVertragsbeginn(Date vertragsbeginn) {
		this.vertragsbeginn = vertragsbeginn;
	}

	public Date getVertragsende() {
		return vertragsende;
	}

	public void setVertragsende(Date vertragsende) {
		this.vertragsende = vertragsende;
	}
	public Kunde getKunde(){
		return this.kunde;
	}
	
	public void setKunde(Kunde kunde){
		this.kunde = kunde;
	}
	
	public Set<Feedback> getFeedback()
	{
		return this.feedback;
	}
	public void setFeedback(Set<Feedback> feedback){
		this.feedback = feedback;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		final Vertrag other = (Vertrag) obj;
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
