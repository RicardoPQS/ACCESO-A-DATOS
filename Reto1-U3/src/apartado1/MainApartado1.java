package apartado1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainApartado1 {

	public static void main(String[] args) {
		crear();
	        
	}

	public static void crear() {
		Continente continente = new Continente(1,"Atlantis");
		Continente continente2 = new Continente(2,"Fondo de Bikini");
		Continente continente3 = new Continente(3,"AquaLand");
		EntityManagerFactory emf =
	            Persistence.createEntityManagerFactory("Naciones");
	        EntityManager em = emf.createEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.persist(continente);
	            em.persist(continente2);
	            em.persist(continente3);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	}
	
}

