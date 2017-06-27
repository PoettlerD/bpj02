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

import at.campus02.bp2.model.Ansprechpartner;
import at.campus02.bp2.model.Kunde;
import at.campus02.bp2.model.Person;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@ManagedBean
@SessionScoped
public class AnsprechpartnerBean implements Serializable {

	private EntityManager entityManager;

	private Ansprechpartner newAnsprechpartner = new Ansprechpartner();
	private List<Ansprechpartner> ansprechpartnerListe = new ArrayList<Ansprechpartner>();
	private List<Kunde> kundeListe = new ArrayList<Kunde>();
	private Ansprechpartner selectedAnsprechpartner;
	public AnsprechpartnerBean(){
	}

	@PostConstruct
	public void createEntityManager() {
		entityManager = EntityManagerFactoryProvider.get().createEntityManager();
	}

	@PreDestroy
	public void closeEntityManager() {
		entityManager.close();
	}
	
	public void loadAnsprechpartnerFromDB() {
		ansprechpartnerListe = entityManager.createQuery("from Ansprechpartner", Ansprechpartner.class).getResultList();
	}
	
	public void save() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(newAnsprechpartner);
		transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Ansprechpartner " + newAnsprechpartner.getPerson().getName() + " wurde gespeichert"));
	}

	public List<Ansprechpartner> getAnsprechpartnerListe() {
		loadAnsprechpartnerFromDB();
		return ansprechpartnerListe;
	}
	
	public void loadKundenFromDB() {
		kundeListe = entityManager.createQuery("from Kunde", Kunde.class).getResultList();
	}
	
	public List<Kunde> getKundenListe() {
		loadKundenFromDB();
		return kundeListe;
	}
	
	public void setAnsprechpartnerListe(List<Ansprechpartner> ansprechpartnerListe) {
		this.ansprechpartnerListe = ansprechpartnerListe;
	}

	public Ansprechpartner getNewAnsprechpartner() {
		return newAnsprechpartner;
	}
	public void setNewAnsprechpartner(Ansprechpartner newAnsprechpartner) {
		this.newAnsprechpartner = newAnsprechpartner;
	}
    public Ansprechpartner getSelectedAnsprechpartner() {
        return selectedAnsprechpartner;
    }
    public void setSelectedAnsprechpartner(Ansprechpartner selectedAnsprechpartner){
    	this.selectedAnsprechpartner = selectedAnsprechpartner;
    }
    public void deleteAnsprechpartner() {
    	
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Person ansprechpartnerPerson = selectedAnsprechpartner.getPerson();
		entityManager.remove(selectedAnsprechpartner);
		entityManager.remove(ansprechpartnerPerson);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Ansprechpartner wurde gelöscht"));
        selectedAnsprechpartner = null;
    }
    
    public void onRowEdit(RowEditEvent event) {
       
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(selectedAnsprechpartner);
		entityManager.merge(selectedAnsprechpartner.getPerson());
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Ansprechpartner wurde gespeichert"));
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


}
