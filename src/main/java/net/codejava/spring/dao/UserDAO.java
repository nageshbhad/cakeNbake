package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.CakeTO;
import net.codejava.spring.model.CartTo;
import net.codejava.spring.model.DoctorWhereAbouts;

public interface UserDAO {
	public List<CakeTO> getHomeCakes();

	public void addToCart(CartTo cartTo);

	public List<CakeTO> filterByOccasion(String occasion);

	public List<CakeTO> filterByCakeType(String cakeType);

	public CakeTO filterByCakeId(int i);
}
