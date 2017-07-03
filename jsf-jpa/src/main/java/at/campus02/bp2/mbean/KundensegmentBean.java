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

import at.campus02.bp2.model.Kundensegment;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@ManagedBean
@SessionScoped
public class KundensegmentBean {


	private EntityManager entityManager;

	private Kundensegment newKundensegment = new Kundensegment();
	private List<Kundensegment> kundensegmentListe = new ArrayList<Kundensegment>();
	private Kundensegment selectedKundensegment;
	public KundensegmentBean(){
	}

	@PostConstruct
	public void createEntityManager() {
		entityManager = EntityManagerFactoryProvider.get().createEntityManager();
	}

	@PreDestroy
	public void closeEntityManager() {
		entityManager.close();
	}
	
	public void loadKundensegmentFromDB() {
		kundensegmentListe = entityManager.createQuery("from Kundensegment", Kundensegment.class).getResultList();
	}
	
	public void save() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(newKundensegment);
		transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Kundensegment " + newKundensegment.getName() + " wurde gespeichert"));
	}

	public List<Kundensegment> getKundensegmentListe() {
		loadKundensegmentFromDB();
		return kundensegmentListe;
	}
	public void setKundensegmentListe(List<Kundensegment> kundensegmentListe) {
		this.kundensegmentListe = kundensegmentListe;
	}

	public Kundensegment getNewKundensegment() {
		System.out.print("KUNDENSEGMENT:");
		System.out.println(newKundensegment);
		System.out.println(newKundensegment.getName());
		return newKundensegment;
	}
	public void setNewKundensegment(Kundensegment newKundensegment) {
		this.newKundensegment = newKundensegment;
	}

    public Kundensegment getSelectedKundensegment() {
		return selectedKundensegment;
	}

	public void setSelectedKundensegment(Kundensegment selectedKundensegment) {
		this.selectedKundensegment = selectedKundensegment;
	}

	public void deleteKundensegment() {
    	
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(selectedKundensegment);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Kundensegment wurde gelöscht"));
        selectedKundensegment = null;
    }
    
    public void onRowEdit(RowEditEvent event) {
       
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(selectedKundensegment);
		transaction.commit();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Das Kundensegment wurde gespeichert"));
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


}
