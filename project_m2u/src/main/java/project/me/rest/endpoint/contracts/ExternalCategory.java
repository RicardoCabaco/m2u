package project.me.rest.endpoint.contracts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import project.me.model.Product;



public class ExternalCategory {


	private long id;


	private String name;


	public List<ExternalProduct> extProd = new ArrayList<>();


	ExternalCategory() {
	}

	public ExternalCategory(String name) {
		this.setName(name);
	}
	

	   public static ExternalCategory of(final long id, final String nome) {
	        final ExternalCategory extCat = new ExternalCategory();
	        extCat.setId(id);
	        extCat.setName(nome);

	        return extCat;
	    }

	
    public static ExternalCategory of(final long id, final String nome, Set<Product> product) {
        final ExternalCategory extCat = new ExternalCategory();
        extCat.setId(id);
        extCat.setName(nome);
        extCat.extProd = new ArrayList<>();
        
        for(Product p : product)
        	extCat.extProd.add(ExternalProduct.of(p.getId(),p.getName(),p.getDescription()));
        
        return extCat;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
