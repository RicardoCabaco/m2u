package project.me.dao;

import java.util.List;

import project.me.model.Product;


public interface ProductDao {

	public abstract Product getProductById(long productId);
	
	public abstract Product createProduct(Product product);
	
    public abstract List<Product> getAllProducts();
    
    public abstract void removeProduct(long productId);

	public abstract Product updateProduct(Product product);

}