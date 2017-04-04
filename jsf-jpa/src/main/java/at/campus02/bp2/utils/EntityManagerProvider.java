package at.campus02.bp2.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerProvider {

	private static EntityManager entityManager;

	public static EntityManager get() {
		if (entityManager == null) {
			entityManager = Persistence.createEntityManagerFactory("jpa-example").createEntityManager();
		}
		return entityManager;
	}
}
