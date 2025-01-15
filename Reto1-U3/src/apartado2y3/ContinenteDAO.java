package apartado2y3;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ContinenteDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Naciones");
	
	public void crearContinente(Continente continente) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(continente);
		em.getTransaction().commit();
		em.close();
	}
	
	public Continente leerContinente(int id) {
		EntityManager em = emf.createEntityManager();
		Continente continente = em.find(Continente.class, id);
		em.close();
		return continente;
	}
	
	public void actualizarContinente(Continente continente) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(continente);
		em.getTransaction().commit();
		em.close();
	}
	
	public void eliminarContinente(int id) {
		EntityManager em = emf.createEntityManager();
		Continente continete = em.find(Continente.class, id);
		if(continete != null) {
			em.getTransaction().begin();
			em.remove(continete);
			em.getTransaction().commit();
		}
		em.close();
	}
	
	// Crear una nueva región y asociarla a un continente
    public void agregarRegion(int continenteId, Region region) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Continente continente = em.find(Continente.class, continenteId);
        if (continente != null) {
            continente.getRegiones().add(region);
            region.setContinente(continente);
            em.merge(continente);
        }
        em.getTransaction().commit();
        em.close();
    }

    // Eliminar una región específica
    public void eliminarRegion(int regionId) {
        EntityManager em = emf.createEntityManager();
        Region region = em.find(Region.class, regionId);
        if (region != null) {
            em.getTransaction().begin();
            em.remove(region);
            em.getTransaction().commit();
        }
        em.close();
    }
	
}
