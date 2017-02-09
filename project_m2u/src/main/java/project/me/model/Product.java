package project.me.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name ="Product")
public class Product implements Serializable{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Size(min=3)
	@Column(name = "name")
	private String name;
	
	@Size(min=3)
	@Column(name = "description")
	private String description;
	
	@OneToMany(cascade = {CascadeType.ALL})
	public Set<Photo> photos = new HashSet<>();
	
	@ManyToOne
	public Category category;


	Product() {
	}

	public Product(String name, String description) {
		this.setName(name);
		this.setDescription(description);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
