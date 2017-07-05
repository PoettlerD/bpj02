package at.campus02.bp2.mbean;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import at.campus02.bp2.model.Standort;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@FacesConverter("at.campus02.bp2.mbean.StandortListConverter")
public class StandortListConverter implements Converter {

    private EntityManager entityManager;

    public StandortListConverter() {
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
        Standort s = entityManager.find(Standort.class, id);
        return s;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().isEmpty()) {
            return "";
        }
        Long id = ((Standort) value).getId();
        return id.toString();
    }
}