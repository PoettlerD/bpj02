package at.campus02.bp2.model;

import java.io.Serializable;
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

@Entity
@Table(name = "ANSPRECHPARTNER")
public class Ansprechpartner implements Serializable {

	private static final long serialVersionUID = -4721951242117090563L;
	
	public Ansprechpartner(){
		person = new Person();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "IstWeisungsbefugt", unique = false, nullable = false)
	private boolean istWeisungsbefugt;
	
	@Column(name= "Zustaendigkeit", unique = false, nullable = false)
	private String zustaendigkeit;
	
	@ManyToOne
	@JoinColumn(name="KundeID", nullable=false)
	private Kunde kunde;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToOne(orphanRemoval = true) //Ein Ansprechpartner muss eine Person haben (sein), aber in der Personen-Klasse wird nicht auf die Ansprechpartner referenziert
	@JoinColumn(name="PersonId", nullable=false)
	private Person person;
	
	public Person getPerson(){
		return this.person;
	}
	
	public void setPerson(Person person){
		this.person = person;
	}
	
	public Kunde getKunde(){
		return this.kunde;
	}
	
	public void setKunde(Kunde kunde){
		this.kunde = kunde;
	}
	
	public boolean getIstWeisungsbefugt(){
		return this.istWeisungsbefugt;
	}
	
	public void setIstWeisungsbefugt(boolean istWeisungsbefugt){
		this.istWeisungsbefugt = istWeisungsbefugt;
	}
	
	public String getZustaendigkeit(){
		return this.zustaendigkeit;
	}
	
	public void setZustaendigkeit(String zustaendigkeit){
		this.zustaendigkeit = zustaendigkeit;
	}
	
}
