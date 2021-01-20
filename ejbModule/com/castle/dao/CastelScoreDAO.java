package com.castle.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.castle.entities.CastleScore;
import com.castle.entities.User;
import com.castle.entities.Castle;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class CastelScoreDAO {
	private final static String UNIT_NAME = "castlePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(CastleScore castle) {
		em.persist(castle);
	}

	public CastleScore merge(CastleScore castle) {
		return em.merge(castle);
	}

	public void remove(CastleScore castle) {
		em.remove(em.merge(castle));
	}

	public CastleScore find(Object id) {
		return em.find(CastleScore.class, id);
	}

	public List<CastleScore> getFullList() {
		List<CastleScore> list = null;

		Query query = em.createQuery("select c from Castle c");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<CastleScore> userExists(User user) {
		List<CastleScore> list = null;
		String select = "select s ";
		String from = "from CastleScore s ";
		String where = "where s.user like :user ";
		String orderby = "";
		
		Query query = em.createQuery(select + from + where + orderby);
		query.setParameter("user", user);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<CastleScore> scoreExists(User user, Castle castle) {
		List<CastleScore> list = null;
		String select = "select s ";
		String from = "from CastleScore s ";
		String where = "where s.castle like :castle and s.user like :user ";
		String orderby = "";
		
		Query query = em.createQuery(select + from + where + orderby);
		query.setParameter("castle", castle);
		query.setParameter("user", user);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<CastleScore> scoreCastles(Castle castle) {
		List<CastleScore> list = null;
		String select = "select s ";
		String from = "from CastleScore s ";
		String where = "where s.castle like :castle ";
		String orderby = "";
		
		Query query = em.createQuery(select + from + where + orderby);
		query.setParameter("castle", castle);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	

	public List<CastleScore> getListVer(Map<String, Object> searchParams) {
		List<CastleScore> list = null;

		// 1. Build query string with parameters
		String select = "select c ";
		String from = "from Castle c ";
		String where = "where c.isVerified like :verified ";
		String orderby = "order by c.name asc, c.name";

		// search for surname
		String name = (String) searchParams.get("name");
		if (name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "c.name like :name ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (name != null) {
			query.setParameter("name", name+"%");
		}
		
		query.setParameter("verified", "no");

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
