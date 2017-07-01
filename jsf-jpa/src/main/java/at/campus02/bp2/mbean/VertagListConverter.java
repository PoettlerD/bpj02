package at.campus02.bp2.mbean;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import at.campus02.bp2.model.Vertrag;
import at.campus02.bp2.utils.EntityManagerFactoryProvider;

@FacesConverter("at.campus02.bp2.mbean.VertragListConverter")
public class VertagListConverter {
    private EntityManager entityManager;

    public VertagListConverter() {
    }
    
    @PostConstruct
	public void createEntityManager() {
		entityManager = EntityManagerFactoryProvider.get().createEntityManager();
	}
    
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.isEmpty()) {
            return null;
        }
        Long id = new Long(value);
        Vertrag p = entityManager.find(Vertrag.class, id);
        return p;
    }
    
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
