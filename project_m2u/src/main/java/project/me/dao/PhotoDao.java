package project.me.dao;

import java.util.List;

import project.me.model.Photo;
import project.me.model.Product;

public interface PhotoDao {

	public abstract Photo getPhotoById(long photoId);
	
	public abstract Photo createPhoto(Photo photo);
	
    public abstract List<Photo> getAllPhotos();
    
    public abstract void removePhoto(long photoId);
    
	public abstract Photo updatePhoto(Photo photo);

}