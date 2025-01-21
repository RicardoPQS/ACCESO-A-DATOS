package apartado1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainApartado1 {

	public static void main(String[] args) {
		crear();
	        leer();
		actualizar(1,"NUEVO");
		borrar(1);
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
	public static void leer() {
		EntityManager em = emf.createEntityManager();
		try {
            List<Continente> continentes = em.createQuery("SELECT c FROM Continente c", Continente.class).getResultList();
            for (Continente c : continentes) {
                System.out.println(c.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            
        }
	}
	
	public static void actualizar(int id, String nuevoNombre) {
	    EntityManager em = emf.createEntityManager();
	    try {
	        em.getTransaction().begin();
	        Continente continente = em.find(Continente.class, id);
	        if (continente != null) {
	            continente.setNombre(nuevoNombre);
	            em.getTransaction().commit();
	            System.out.println("Continente actualizado: " + continente.getNombre());
	        } else {
	            System.out.println("No se encontró el continente con ID: " + id);
	            em.getTransaction().rollback();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        em.getTransaction().rollback();
	    } finally {
	        em.close();
	    }
	}
	
	
	public static void borrar(int id) {
	    EntityManager em = emf.createEntityManager();
	    try {
	        em.getTransaction().begin();
	        Continente continente = em.find(Continente.class, id);
	        if (continente != null) {
	            em.remove(continente);
	            em.getTransaction().commit();
	            System.out.println("Continente eliminado: " + continente.getNombre());
	        } else {
	            System.out.println("No se encontró el continente con ID: " + id);
	            em.getTransaction().rollback();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        em.getTransaction().rollback();
	    } finally {
	        em.close();
	        emf.close();
	    }
	}
	
}

