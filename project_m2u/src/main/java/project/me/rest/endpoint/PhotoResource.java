package project.me.rest.endpoint;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import project.me.dao.PhotoDao;
import project.me.model.Photo;
import project.me.rest.endpoint.contracts.ExternalPhoto;
import project.me.rest.endpoint.contracts.FileUploadForm;

@Stateless
@Path("photo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PhotoResource {
	
	@Inject
	private PhotoDao photoRepository;


    @GET
    public List<ExternalPhoto> getAll() {
    	
    	List<Photo> photos = photoRepository.getAllPhotos();
    	
		List<ExternalPhoto> externalPhotos = new ArrayList<>( photos.size() );

		for (Photo photo : photos ) {
			externalPhotos.add(ExternalPhoto.of(photo.isFront(), photo.getName()));
		}

		return externalPhotos;
    }

    @GET
    @Path("{id}")
    public ExternalPhoto getById(@PathParam("id") final Long id) {

    	Photo prod = photoRepository.getPhotoById(id);

    	ExternalPhoto externalProd = ExternalPhoto.of(prod.isFront(), prod.getName());

        return externalProd;
    }

    @POST
    public Response create(@Valid final ExternalPhoto externalPhoto) {

        Photo p = new Photo(externalPhoto.isFront(), externalPhoto.getName());
		p = photoRepository.createPhoto(p);

        return Response.ok(p).build();
    }
   
    
    

    @PUT
    public Response update(final ExternalPhoto externalPhoto) {

        final Photo p = new Photo(externalPhoto.isFront(), externalPhoto.getName());
        
        photoRepository.updatePhoto(p);

        return Response.status(Status.OK).build();

    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") final Long id) {

        photoRepository.removePhoto(id);

        return Response.status(Status.NO_CONTENT).build();
    }
    
    
}


