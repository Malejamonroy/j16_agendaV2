package com.cursogetafe.agenda.persistencia;

import java.util.HashSet;
import java.util.Set;

import com.cursogetafe.agenda.config.Config;
import com.cursogetafe.agenda.modelo.Contacto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class ContactoDaoJPA implements ContactoDao {

	private EntityManagerFactory emf;
	private EntityManager em;

	public ContactoDaoJPA() {
		emf = Config.getEmf();
	}

	@Override
	public void insertar(Contacto c) {
		em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

		em.close();
	}

	@Override
	public void actualizar(Contacto c) {
		em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}

		em.close();

	}

	@Override
	public boolean eliminar(int idContacto) {
		//opcion 1 
//		em = emf.createEntityManager();
//		try {
//			em.getTransaction().begin();
//
//			Contacto c = em.find(Contacto.class, idContacto);
//			if (c != null) {
//				em.remove(c);
//			}
//			em.getTransaction().commit();
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			em.getTransaction().rollback();
//			return false;
//		} finally {
//			em.close();
//		}
		
		//opcion 2 
		
		em = emf.createEntityManager();
		Contacto eliminar = em.find(Contacto.class, idContacto);
		if (eliminar != null) {

			try {
				em.getTransaction().begin();
				em.remove(eliminar);
				em.getTransaction().commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
				return false;
			} finally {
				em.close();
			}
		} else 
			return false;
		
	}

	@Override
	public boolean eliminar(Contacto c) {
		//primera opcion
//		em = emf.createEntityManager();
//		try {
//			em.getTransaction().begin();
//			em.remove(c);
//			em.getTransaction().commit();
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();// hacer un log
//			em.getTransaction().rollback();
//			return false;
//		} finally {
//			em.close();
//		}
		
		//segunda forma llamamos al metodo de arriba 
		return eliminar(c.getIdContacto());
	}
	
	//Debe retornar el contacto con sus telefonos y correos

	@Override
	public Contacto buscar(int idContacto) {
		em = emf.createEntityManager();
		
		String Consjpql = "select c from Contacto c where c.idContacto = :id ";
		TypedQuery<Contacto> busPorId = em.createQuery(Consjpql,Contacto.class);
		busPorId.setParameter("id", idContacto);
		Contacto c = busPorId.getSingleResultOrNull();
		if(c != null) {
		c.getTelefonos().size();
		c.getCorreos().size();
		}
		em.close();
		return c;
	}

	//Debe retornar el contacto sin sus telefonos y correos
	@Override
	public Set<Contacto> buscar(String cadena) {
		em= emf.createEntityManager();
		
		String Consujpql ="Select c from Contacto c where c.nombre like :cad "
				+ "or c.apellidos like :cad or c.apodo like :cad ";
		TypedQuery<Contacto> busContCadena = em.createQuery(Consujpql,Contacto.class);
		busContCadena.setParameter("cad", "%" + cadena + "%");
		
		Set<Contacto> resBusqueda = new HashSet<Contacto>(busContCadena.getResultList());
		em.close();
		return resBusqueda;
	}

	@Override
	public Set<Contacto> buscarTodos() {
		em= emf.createEntityManager();
		
		
		String consultajpql = "select c from Contacto c";
		
		TypedQuery<Contacto> resConsultajpql= em.createQuery(consultajpql,Contacto.class);
		Set<Contacto> buscarContactoTodos = new HashSet<Contacto>(resConsultajpql.getResultList());
		em.close();
		return buscarContactoTodos;
	}

}
