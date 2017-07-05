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
import at.campus02.bp2.model.Vertrag;
import at.campus02.bp2.model.Feedback;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@ManagedBean
@SessionScoped
public class FeedbackBean {
	private EntityManager entityManager;

	private Feedback newFeedback = new Feedback();
	private List<Feedback> feedbackListe = new ArrayList<Feedback>();
	private List<Feedback> filteredFeedback = new ArrayList<Feedback>();
	private List<Vertrag> vertragsListe = new ArrayList<Vertrag>();
	private Feedback selectedFeedback;
	public FeedbackBean(){}
	
	@PostConstruct
	public void createEntityManager() {
		entityManager = EntityManagerFactoryProvider.get().createEntityManager();
	}

	@PreDestroy
	public void closeEntityManager() {
		entityManager.close();
	}
	public void loadFeedbackFromDB() {
		feedbackListe = entityManager.createQuery("from Feedback", Feedback.class).getResultList();
	}
	public void save() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(newFeedback);
		transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Vertrag vom" + newFeedback.getDatum() + " wurde gespeichert"));
	}
	
	public List<Feedback> getFeedbackListe() {
		loadFeedbackFromDB();
		return feedbackListe;
	}
	public void setFeedbackListe(List<Feedback> feedbackListe) {
		this.feedbackListe = feedbackListe;
	}
	public Feedback getNewFeedback() {
		return newFeedback;
	}
	public void setNewFeedback(Feedback newFeedback) {
		this.newFeedback = newFeedback;
	}
    public Feedback getSelectedFeedback() {
        return selectedFeedback;
    }
    public void setSelectedFeedback(Feedback selectedFeedback){
    	this.selectedFeedback = selectedFeedback;
    }
	public void loadVertraegeFromDB() {
		vertragsListe = entityManager.createQuery("from Vertrag", Vertrag.class).getResultList();
	}

	public List<Vertrag> getVertragsListe() {
		loadVertraegeFromDB();
		return vertragsListe;
	}
	
public void deleteFeedback() {
    	
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(selectedFeedback);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Feedback wurde gelöscht"));
        selectedFeedback = null;
    }
    
    public void onRowEdit(RowEditEvent event) {
       
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(selectedFeedback);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "das Feedback wurde gespeichert"));
    }
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public List<Feedback> getFilteredFeedback() {
    	return filteredFeedback;
    } 
    public void setFilteredFeedback(List<Feedback> filteredFeedback) {
    	this.filteredFeedback = filteredFeedback;
 	}

}