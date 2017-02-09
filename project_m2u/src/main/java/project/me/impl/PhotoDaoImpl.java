package project.me.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import project.me.dao.PhotoDao;
import project.me.dao.ProductDao;
import project.me.model.Photo;
import project.me.model.Product;


@ApplicationScoped
public class PhotoDaoImpl implements PhotoDao {

	@PersistenceContext(unitName="hike-PU-JTA")
	private EntityManager entityManager;
	

	@Override
	public Photo getPhotoById(long photoId) {
		return entityManager.find( Photo.class, photoId );
	}

	@Override
	public Photo createPhoto(Photo photo) {
		entityManager.persist(photo);
		return photo;
	}

	@Override
	public List<Photo> getAllPhotos() {
		return entityManager.createQuery( "from Photo", Photo.class ).getResultList();
	}

	@Override
	public void removePhoto(long photoId) {
		entityManager.remove(entityManager.find( Photo.class, photoId ));
		
	}

	@Override
	public Photo updatePhoto(Photo photo) {
		entityManager.merge(photo);
		return photo;
	}



}