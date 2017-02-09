package project.me.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import project.me.dao.CategoryDao;
import project.me.dao.ProductDao;
import project.me.model.Category;
import project.me.model.Photo;
import project.me.model.Product;


@ApplicationScoped
public class CategoryDaoImpl implements CategoryDao {

	@PersistenceContext(unitName="hike-PU-JTA")
	private EntityManager entityManager;
	

	@Override
	public Category getCategoryById(long categoryId) {
		return entityManager.find( Category.class, categoryId );
	}

	@Override
	public Category createCategory(Category category) {
		entityManager.persist(category);
		return category;
	}

	@Override
	public List<Category> getAllCategorys() {
		return entityManager.createQuery( "from Category", Category.class ).getResultList();
	}

	@Override
	public void removeCategory(long categoryId) {
		entityManager.remove(entityManager.find( Category.class, categoryId ));
		
	}

	@Override
	public Category updateCategory(Category cat) {
		return entityManager.merge(cat);
		
	}



}