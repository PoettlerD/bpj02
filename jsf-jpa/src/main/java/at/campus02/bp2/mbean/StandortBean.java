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

import at.campus02.bp2.model.Article;
import at.campus02.bp2.model.Kunde;
import at.campus02.bp2.model.Person;
import at.campus02.bp2.model.Standort;
import at.campus02.bp2.model.Vertrag;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@ManagedBean
@SessionScoped
public class StandortBean implements Serializable {
	
	private EntityManager entityManager;

	private Standort newStandort = new Standort();
	private List<Standort> standortListe = new ArrayList<Standort>();
	private List<Standort> filteredStandorte = new ArrayList<Standort>();
	private List<Kunde> kundeListe = new ArrayList<Kunde>();
	private Standort selectedStandort;
	public StandortBean(){}
	
	@PostConstruct
	public void createEntityManager() {
		entityManager = EntityManagerFactoryProvider.get().createEntityManager();
	}

	@PreDestroy
	public void closeEntityManager() {
		entityManager.close();
	}
	public void loadStandorteFromDB() {
		standortListe = entityManager.createQuery("from Standort", Standort.class).getResultList();
	}
	public void save() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(newStandort);
		transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Standort " + newStandort.getAdresse() + " wurde gespeichert"));
	}
	
	public List<Standort> getstandortListe() {
		loadStandorteFromDB();
		return standortListe;
	}
	public void setStandortListe(List<Standort> standortListe) {
		this.standortListe = standortListe;
	}

	public Standort getNewStandort() {
		return newStandort;
	}
	public void setNewStandort(Standort newStandort) {
		this.newStandort = newStandort;
	}
    public Standort getSelectedStandort() {
        return selectedStandort;
    }
    public void setSelectedStandort(Standort selectedStandort){
    	this.selectedStandort = selectedStandort;
    }
	public void loadStandortFromDB() {
		standortListe = entityManager.createQuery("from Standort", Standort.class).getResultList();
	}
	
	public List<Standort> getStandortListe() {
		loadStandortFromDB();
		return standortListe;
	}

public void deleteStandort() {
    	
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(selectedStandort);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Die Standort wurde gelöscht"));
        selectedStandort = null;
    }
    
    public void onRowEdit(RowEditEvent event) {
       
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(selectedStandort);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Standort wurde gespeichert"));
    }
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public List<Standort> getfilteredStandorte() {
    	return filteredStandorte;
    } 
    public void setFilteredVertraege(List<Standort> filteredStandorte) {
    	this.filteredStandorte = filteredStandorte;
 	}
}
