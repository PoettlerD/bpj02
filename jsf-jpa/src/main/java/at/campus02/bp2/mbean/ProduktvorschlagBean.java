package at.campus02.bp2.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import at.campus02.bp2.model.Ansprechpartner;
import at.campus02.bp2.model.Kunde;
import at.campus02.bp2.model.Person;
import at.campus02.bp2.model.Produkt;
import at.campus02.bp2.model.Produktvorschlag;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@ManagedBean
@SessionScoped
public class ProduktvorschlagBean {

	private EntityManager entityManager;
	
	private Produktvorschlag produktvorschlag = new Produktvorschlag();
	private Produkt selectedProdukt;
	
	public ProduktvorschlagBean(){
	}

	@PostConstruct
	public void createEntityManager() {
		entityManager = EntityManagerFactoryProvider.get().createEntityManager();
	}

	@PreDestroy
	public void closeEntityManager() {
		entityManager.close();
	}
	
	public void loadProduktvorschlaegeFromDB() {
		if(produktvorschlag.getKunde() == null)
		{
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Es ist kein Kunde festgelegt."));
	        return;
		}
		if(produktvorschlag.getStandort() == null)
		{
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Es ist kein Standort festgelegt."));
	        return;
		}
		if(produktvorschlag.getProdukttyp() == null || produktvorschlag.getProdukttyp() == "")
		{
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Es ist kein Produkttyp festgelegt."));
	        return;
		}
		Query query = entityManager.createQuery("from Produkt p where p.produkttyp = :arg1 order by p.preis desc", Produkt.class);
		query.setParameter("arg1", produktvorschlag.getProdukttyp());
		produktvorschlag.setProdukte(query.getResultList());
		filterProduktvorschlaegeByGis();
	}
	
	/*MAGICALLY FILTER PRODUCTTYPES*/
	private void filterProduktvorschlaegeByGis(){
		int plz = produktvorschlag.getStandort().getPlz();
		int filterRemover = plz % 6;
		List<Produkt> produkte = produktvorschlag.getProdukte();
		while(produkte.size() > 3){
			produkte.remove(filterRemover % produkte.size());
		}
		produktvorschlag.setProdukte(produkte);
	}
	
	public void load(){
		produktvorschlag.setProdukte(new ArrayList<Produkt>());
		loadProduktvorschlaegeFromDB();
	}
	
	public void setProduktvorschlag(Produktvorschlag produktvorschlag){
		this.produktvorschlag = produktvorschlag;
	}
	
	public Produktvorschlag getProduktvorschlag(){
		return this.produktvorschlag;
	}
	
	public void setSelectedProdukt(Produkt produkt){
		this.selectedProdukt = produkt;
	}
	
	public Produkt getSelectedProdukt(){
		return this.selectedProdukt;
	}
}
