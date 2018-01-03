package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.CakeTO;
import net.codejava.spring.model.CartTo;
import net.codejava.spring.model.DoctorWhereAbouts;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class UserDAOImpl implements UserDAO {
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<CakeTO> getHomeCakes() {
		String hql = "FROM CakeTO";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<CakeTO> listUser = (List<CakeTO>) query.list();
		return listUser;
	}


	@Override
	@Transactional
	public void addToCart(CartTo cartTo) {
		if(cartTo!=null){
			sessionFactory.getCurrentSession().save(cartTo);
		}
	}

	@Override
	@Transactional
	public List<CakeTO> filterByOccasion(String occasion) {
		String hql = "FROM CakeTO where occassion = :occasion";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("occasion",occasion);
		List<CakeTO> listUser = (List<CakeTO>) query.list();
		return listUser;
	}

	@Override
	@Transactional
	public List<CakeTO> filterByCakeType(String cakeType) {
		String hql = "FROM CakeTO where type = :cakeType";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("cakeType",cakeType);
		List<CakeTO> listUser = (List<CakeTO>) query.list();
		return listUser;
	}

	@Override
	@Transactional
	public CakeTO filterByCakeId(int cakeId) {
		String hql = "FROM CakeTO where cakeId = :cakeId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("cakeId",cakeId);
		CakeTO cakeTO = (CakeTO)query.list().get(0);
		return cakeTO;
	}

}
