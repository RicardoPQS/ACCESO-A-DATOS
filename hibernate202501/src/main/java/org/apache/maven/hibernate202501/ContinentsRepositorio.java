package org.apache.maven.hibernate202501;

import database.Continents;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ContinentsRepositorio {
    private final SessionFactory sessionFactory;

    public ContinentsRepositorio(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // 1. Listado de todos los continentes (solo nombre)
    public List<String> obtenerNombresContinentes() {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT c.name FROM Continents c";
            return session.createQuery(jpql, String.class).getResultList();
        }
    }

    // 2. Listado filtrado por nombre específico
    public List<Continents> buscarPorNombre(String nombre) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "FROM Continents c WHERE c.name = :name";
            return session.createQuery(jpql, Continents.class)
                    .setParameter("name", nombre)
                    .getResultList();
        }
    }

    // 3. Listado ordenado por nombre
    public List<Continents> obtenerContinentesOrdenados() {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "FROM Continents c ORDER BY c.name ASC";
            return session.createQuery(jpql, Continents.class).getResultList();
        }
    }

    // 4. Listado de continentes cuyo nombre comienza con una letra
    public List<Continents> buscarPorInicial(String inicial) {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "FROM Continents c WHERE c.name LIKE :inicial";
            return session.createQuery(jpql, Continents.class)
                    .setParameter("inicial", inicial + "%")
                    .getResultList();
        }
    }

    // 7. Listado de Continentes con sus Regiones asociadas
    public List<Object[]> obtenerContinentesConRegiones() {
        try (Session session = sessionFactory.openSession()) {
            String jpql = "SELECT c.name, r.name FROM Continents c JOIN c.regionses r";
            return session.createQuery(jpql).getResultList();
        }
    }
}
