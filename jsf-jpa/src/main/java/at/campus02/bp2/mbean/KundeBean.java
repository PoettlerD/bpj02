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
import at.campus02.bp2.model.Article;
import at.campus02.bp2.model.Kunde;
import at.campus02.bp2.model.Person;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@ManagedBean
@SessionScoped
public class KundeBean {

	private EntityManager entityManager;

	private Kunde newKunde = new Kunde();
	private List<Kunde> kundeListe = new ArrayList<Kunde>();
	private Kunde selectedKunde;
	public KundeBean(){
	}

	@PostConstruct
	public void createEntityManager() {
		entityManager = EntityManagerFactoryProvider.get().createEntityManager();
	}

	@PreDestroy
	public void closeEntityManager() {
		entityManager.close();
	}
	
	public void loadKundenFromDB() {
		kundeListe = entityManager.createQuery("from Kunde", Kunde.class).getResultList();
	}
	
	public void save() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(newKunde);
		transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Kunde " + newKunde.getKundenname() + " wurde gespeichert"));
	}

	public List<Kunde> getKundenListe() {
		loadKundenFromDB();
		return kundeListe;
	}
	public void setKundenListe(List<Kunde> kundeListe) {
		this.kundeListe = kundeListe;
	}

	public Kunde getNewKunde() {
		return newKunde;
	}
	public void setNewKunde(Kunde newKunde) {
		this.newKunde = newKunde;
	}
    public Kunde getSelectedKunde() {
        return selectedKunde;
    }
    public void setSelectedKunde(Kunde selectedKunde){
    	this.selectedKunde = selectedKunde;
    }
    public void deleteKunde() {
    	
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(selectedKunde);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Kunde wurde gelöscht"));
        selectedKunde = null;
    }
    
    public void onRowEdit(RowEditEvent event) {
       
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(selectedKunde);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Der Kunde wurde gespeichert"));
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


}
