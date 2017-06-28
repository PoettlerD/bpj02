package at.campus02.bp2.mbean;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import at.campus02.bp2.model.Kunde;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@FacesConverter("at.campus02.bp2.mbean.KundeListConverter")
public class KundeListConverter implements Converter {

    private EntityManager entityManager;

    public KundeListConverter() {
    }
    
    @PostConstruct
	public void createEntityManager() {
		entityManager = EntityManagerFactoryProvider.get().createEntityManager();
	}

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	System.out.println("########### KundeListConverter.getAsObject ############");
    	System.out.println("value");
        if (value.isEmpty()) {
            return null;
        }
        Long id = new Long(value);
        Kunde k = entityManager.find(Kunde.class, id);
        return k;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	System.out.println("########### KundeListConverter.getAsString ############");
        if (value == null || value.toString().isEmpty()) {
            return "";
        }
        Long id = ((Kunde) value).getId();
        System.out.println(id.toString());
        return id.toString();
    }
}