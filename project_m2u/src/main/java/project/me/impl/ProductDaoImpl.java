package project.me.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import project.me.dao.ProductDao;
import project.me.model.Product;


@ApplicationScoped
public class ProductDaoImpl implements ProductDao {

	@PersistenceContext(unitName="hike-PU-JTA")
	private EntityManager entityManager;
	
	@Override
	public Product getProductById(long productId) {
		return entityManager.find( Product.class, productId);
	}
	
	@Override
	public Product createProduct(Product product){
	    entityManager.persist(product);
		return product;
	}
	
	@Override
    public List<Product> getAllProducts(){
    	return entityManager.createQuery( "from Product", Product.class ).getResultList();
    }

	@Override
	public void removeProduct(long productId) {
		entityManager.remove(entityManager.find( Product.class, productId));
		
	}

	@Override
	public Product updateProduct(Product product) {
		 entityManager.merge(product);
		 return product;
	}
	


}