package at.campus02.bp2.mbean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import at.campus02.bp2.model.Kunde;
import at.campus02.bp2.model.Person;
import at.campus02.bp2.model.Vertrag;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@ManagedBean
@SessionScoped
public class VertragBean {
	
	private EntityManager entityManager;

	private Vertrag newVertrag = new Vertrag();
	private List<Vertrag> vertragsListe = new ArrayList<Vertrag>();
	private List<Vertrag> filteredVertraege = new ArrayList<Vertrag>();
	private List<Kunde> kundeListe = new ArrayList<Kunde>();
	private Vertrag selectedVertrag;
	public VertragBean(){}
	
	@PostConstruct
	public void createEntityManager() {
		entityManager = EntityManagerFactoryProvider.get().createEntityManager();
	}

	@PreDestroy
	public void closeEntityManager() {
		entityManager.close();
	}
	public void loadVertraegeFromDB() {
		vertragsListe = entityManager.createQuery("from Vertrag", Vertrag.class).getResultList();
	}
	
	public void save() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(newVertrag);
		transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Vertrag " + newVertrag.getVertragsnummer() + " wurde gespeichert"));
	}
	public List<Vertrag> getVertragsListe() {
		loadVertraegeFromDB();
		return vertragsListe;
	}
	public void setVertragsListe(List<Vertrag> vertragsListe) {
		this.vertragsListe = vertragsListe;
	}

	public Vertrag getNewVertrag() {
		return newVertrag;
	}
	public void setNewVertrag(Vertrag newVertrag) {
		this.newVertrag = newVertrag;
	}
    public Vertrag getSelectedVertrag() {
        return selectedVertrag;
    }
    public void setSelectedVertrag(Vertrag selectedVertrag){
    	this.selectedVertrag = selectedVertrag;
    }
	public void loadKundenFromDB() {
		kundeListe = entityManager.createQuery("from Kunde", Kunde.class).getResultList();
	}
	
	public List<Kunde> getKundenListe() {
		loadKundenFromDB();
		return kundeListe;
	}

public void deleteVertrag() {
    	
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(selectedVertrag);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Vertrag wurde gelöscht"));
        selectedVertrag = null;
    }
    
    public void onRowEdit(RowEditEvent event) {
       
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(selectedVertrag);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Vertrag wurde gespeichert"));
    }
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public List<Vertrag> getFilteredVertraege() {
    	return filteredVertraege;
    } 
    public void setFilteredVertraege(List<Vertrag> filteredVertraege) {
    	this.filteredVertraege = filteredVertraege;
 	}
}
