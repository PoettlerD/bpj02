package at.campus02.bp2.mbean;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import at.campus02.bp2.model.Person;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@FacesConverter("at.campus02.bp2.mbean.PersonListConverter")
public class PersonListConverter implements Converter {

    private EntityManager entityManager;

    public PersonListConverter() {
    }
    
    @PostConstruct
	public void createEntityManager() {
		entityManager = EntityManagerFactoryProvider.get().createEntityManager();
	}

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.isEmpty()) {
            return null;
        }
        Long id = new Long(value);
        Person p = entityManager.find(Person.class, id);
        return p;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	System.out.println("#-#-#-#-#-#-#-#-#-#-#-#-#-#");
    	System.out.println(value.toString());
    	System.out.println(((Person)value).getId());
        if (value == null || value.toString().isEmpty()) {
            return "";
        }
        Long id = ((Person) value).getId();
        if(id == null)
        	return "";
        return id.toString();
    }
}