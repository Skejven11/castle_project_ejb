package com.castle.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.castle.entities.User;
import com.castle.dao.UserDAO;


@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "castlePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(User user) {
		em.persist(user);
	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object id) {
		return em.find(User.class, id);
	}

	public List<User> getUsers() {
		List<User> list = null;

		Query query = em.createQuery("select u from User u");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public String getUserRole()
	{
		return "0";
	}

	public List<User> getUser(String login, String pass) {
		List<User> user = null;

		String select = "select u ";
		String from = "from User u ";
		String where = "";
		String orderby = "";

		if (login != null&&pass != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.login like :login and u.password like :pass ";
		}
		
		Query query = em.createQuery(select + from + where + orderby);

			query.setParameter("login", login);
			query.setParameter("pass", pass);
		
		try {
			user = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

}
