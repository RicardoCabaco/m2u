package project.me.model;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="Category")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotNull
	private String name;

	@OneToMany(cascade = {CascadeType.ALL})
	public Set<Product> product = new HashSet<>();


	Category() {
	}

	public Category(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
