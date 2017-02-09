package project.me.dao;

import java.util.List;

import project.me.model.Category;
import project.me.model.Photo;
import project.me.model.Product;


public interface CategoryDao {

	public abstract Category getCategoryById(long categoryId);
	
	public abstract Category createCategory(Category category);
	
    public abstract List<Category> getAllCategorys();
    
    public abstract void removeCategory(long categoryId);
    
	public abstract Category updateCategory(Category cat);

}