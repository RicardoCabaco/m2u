package project.me.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="Photo")
public class Photo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotNull
	private boolean front;
	
	@NotNull
	private String name;

	@ManyToOne(cascade = {CascadeType.ALL})
	public Product product;


	Photo() {
	}

	public Photo(boolean front, String name) {
		this.setFront(front);
		this.setName(name);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isFront() {
		return front;
	}

	public void setFront(boolean front) {
		this.front = front;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
