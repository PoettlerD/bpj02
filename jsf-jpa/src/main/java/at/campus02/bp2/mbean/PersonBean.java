package at.campus02.bp2.mbean;

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

import at.campus02.bp2.model.Article;
import at.campus02.bp2.model.Person;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@ManagedBean
@SessionScoped
public class PersonBean {

	private EntityManager entityManager;

	private Person newPerson = new Person();
	private List<Person> personenListe = new ArrayList<Person>();
	
	public PersonBean(){
	}

	@PostConstruct
	public void createEntityManager() {
		entityManager = EntityManagerFactoryProvider.get().createEntityManager();
	}

	@PreDestroy
	public void closeEntityManager() {
		entityManager.close();
	}
	
	public void loadPersonenFromDB() {
		personenListe = entityManager.createQuery("from Person", Person.class).getResultList();
	}
	
	public void save() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(newPerson);
		transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Die Person " + newPerson.getName() + " wurde gespeichert"));
	}

	public List<Person> getPersonenListe() {
		loadPersonenFromDB();
		return personenListe;
	}
	public void setPersonenListe(List<Person> personenListe) {
		this.personenListe = personenListe;
	}

	public Person getNewPerson() {
		return newPerson;
	}
	public void setNewPerson(Person newPerson) {
		this.newPerson = newPerson;
	}
}
