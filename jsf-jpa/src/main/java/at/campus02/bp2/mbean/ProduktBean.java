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
import at.campus02.bp2.model.Vertrag;
import at.campus02.bp2.model.Produkt;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@ManagedBean
@SessionScoped
public class ProduktBean implements Serializable {
	
	private EntityManager entityManager;

	private Produkt newProdukt = new Produkt();
	private List<Produkt> produktListe = new ArrayList<Produkt>();
	private List<Produkt> filteredProdukte = new ArrayList<Produkt>();
	private Produkt selectedProdukt;
	public ProduktBean(){}
	
	@PostConstruct
	public void createEntityManager() {
		entityManager = EntityManagerFactoryProvider.get().createEntityManager();
	}

	@PreDestroy
	public void closeEntityManager() {
		entityManager.close();
	}
	public void loadProdukteFromDB() {
		produktListe = entityManager.createQuery("from Produkt", Produkt.class).getResultList();
	}
	
	public void save() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(newProdukt);
		transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Produkt " + newProdukt.getProduktname() + " wurde gespeichert"));
	}

	
	public void setProduktListe(List<Produkt> produktListe) {
		this.produktListe = produktListe;
	}

	public Produkt getNewProdukt() {
		return newProdukt;
	}
	public void setNewProdukt(Produkt newProdukt) {
		this.newProdukt = newProdukt;
	}
    public Produkt getSelectedProdukt() {
        return selectedProdukt;
    }
    public void setSelectedProdukt(Produkt selectedProdukt){
    	this.selectedProdukt = selectedProdukt;
    }
	public void loadProduktFromDB() {
		produktListe = entityManager.createQuery("from Produkt", Produkt.class).getResultList();
	}
	
	public List<Produkt> getProduktListe() {
		loadProduktFromDB();
		return produktListe;
	}

public void deleteProdukt() {
    	
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(selectedProdukt);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Produkt wurde gelöscht"));
        selectedProdukt = null;
    }
    
    public void onRowEdit(RowEditEvent event) {
       
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(selectedProdukt);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Produkt wurde gespeichert"));
    }
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public List<Produkt> getFilteredProdukte() {
    	return filteredProdukte;
    } 
    public void setFilteredProdukte(List<Produkt> filteredProdukte) {
    	this.filteredProdukte = filteredProdukte;
 	}
}
