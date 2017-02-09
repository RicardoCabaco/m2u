package project.me.rest.endpoint.contracts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import project.me.model.Photo;
import project.me.model.Product;


public class ExternalProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    
    @NotNull
    @Size(min = 1, max = 100)
    private String description;

	public List<ExternalPhoto> extPhoto = new ArrayList<>();

    public static ExternalProduct of(final String nome, final String description) {
        final ExternalProduct extProd = new ExternalProduct();
        extProd.setName(nome);
        extProd.setDescription(description);
        return extProd;
    }
    
    public static ExternalProduct of(final Long id, final String nome, final String description) {
        final ExternalProduct extProd = of(nome,description);
        extProd.id = id;
        
        extProd.extPhoto = new ArrayList<>();
        
        return extProd;
    }
    

    public static ExternalProduct of(final Long id, final String nome, final String description, final Set<Photo> photos) {
        final ExternalProduct extProd = of(nome,description);
        extProd.id = id;
        
        extProd.extPhoto = new ArrayList<>();
        
        for(Photo p : photos)
        	extProd.extPhoto.add(ExternalPhoto.of(p.isFront(),p.getName()));
        
        
        return extProd;
    }
    
    

    public Long getId() {
        return this.id;
    }

    protected void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(final String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExternalProduct other = (ExternalProduct) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
