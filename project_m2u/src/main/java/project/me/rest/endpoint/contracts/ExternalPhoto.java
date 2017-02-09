package project.me.rest.endpoint.contracts;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


public class ExternalPhoto {


	private long id;


	private boolean front;
	

	private String name;


	public ExternalProduct product;


	ExternalPhoto() {
	}

	public ExternalPhoto(boolean front, String name) {
		this.setFront(front);
		this.setName(name);
	}
	
   public static ExternalPhoto of(boolean front, String name) {
        final ExternalPhoto extPhoto = new ExternalPhoto();
        extPhoto.setFront(front);
        extPhoto.setName(name);
		return extPhoto;
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
