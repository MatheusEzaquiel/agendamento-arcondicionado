package com.mbe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.mbe.enitity.ArCondicionado;
import com.mbe.util.JPAUtil;


public class ArCondicionadoDAO {

	public static List<ArCondicionado> listar() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query query = em.createQuery("select ac from ArCondicionado ac");
		List<ArCondicionado> retorno = query.getResultList();
		em.close();
		return retorno;
	}
	
	public static void salvar(ArCondicionado arCondicionado) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(arCondicionado);
		em.getTransaction().commit();
		em.close();
	}

	public static void atualizar(ArCondicionado arCondicionado) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(arCondicionado);
		em.getTransaction().commit();
		em.close();
	}

	public static void remove(ArCondicionado arCondicionadoParam) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		ArCondicionado arCondicionado = em.find(ArCondicionado.class, arCondicionadoParam.getId());
		em.remove(arCondicionado);
		em.getTransaction().commit();
		em.close();
	}
	
	public static ArCondicionado buscar(ArCondicionado arCondicionadoParam) {
        EntityManager em = JPAUtil.criarEntityManager();
        ArCondicionado arCondicionado = em.find(ArCondicionado.class, arCondicionadoParam.getId());
        em.close();
        return arCondicionado;
    }

	/**
	 *  Buscar ArCondicionado com maior data de Manutenção (Utiliza Namedquery)
	 *  */ 
	public static ArCondicionado maxDataManutencao(){
		EntityManager em = JPAUtil.criarEntityManager();
		TypedQuery<ArCondicionado> q = em.createNamedQuery("ArCondicionado.maxDataManutencao", ArCondicionado.class);
		ArCondicionado arCondicionadoId = q.getSingleResult(); // Recupera apenas um resultado
		em.close();
		return arCondicionadoId;
	}

}
