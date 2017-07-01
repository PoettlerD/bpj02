package at.campus02.bp2.mbean;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import at.campus02.bp2.model.Vertrag;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@FacesConverter("at.campus02.bp2.mbean.VertragListConverter")
public class VertragListConverter implements Converter {
    private EntityManager entityManager;

    public VertragListConverter() {
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
        Vertrag p = entityManager.find(Vertrag.class, id);
        return p;
    }
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	System.out.println("#-#-#-#-#-#-#-#-#-#-#-#-#-#");
    	System.out.println(value.toString());
    	System.out.println(((Vertrag)value).getId());
        if (value == null || value.toString().isEmpty()) {
            return "";
        }
        Long id = ((Vertrag) value).getId();
        if(id == null)
        	return "";
        return id.toString();
    }
}
