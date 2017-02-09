package project.me.rest.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import project.me.dao.ProductDao;
import project.me.model.Category;
import project.me.model.Photo;
import project.me.model.Product;
import project.me.rest.endpoint.contracts.ExternalCategory;
import project.me.rest.endpoint.contracts.ExternalPhoto;
import project.me.rest.endpoint.contracts.ExternalProduct;

@Stateless
@Path("products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
	
	@Inject
	private ProductDao productRepository;


    @GET
    public List<ExternalProduct> getAll() {
    	
    	List<Product> products = productRepository.getAllProducts();
    	
		List<ExternalProduct> externalProducts = new ArrayList<>( products.size() );

		for (Product product : products ) {
			externalProducts.add(ExternalProduct.of(product.getId(), product.getName(), product.getDescription()));
		}

		return externalProducts;
    }

    @GET
    @Path("{id}")
    public ExternalProduct getById(@PathParam("id") final Long id) {

    	Product prod = productRepository.getProductById(id);

    	ExternalProduct externalProd = ExternalProduct.of(prod.getId(), prod.getName(), prod.getDescription());

        return externalProd;
    }

    @POST
    public Response create(@Valid final ExternalProduct externalProduct) {

        Product p = new Product(externalProduct.getName(),externalProduct.getDescription());
		p = productRepository.createProduct(p);

        return Response.ok(p).build();
    }
   
    
    

    @PUT
    public Response update(final ExternalProduct externalProduct) {

        final Product p = new Product(externalProduct.getName(),  externalProduct.getDescription());
        
        productRepository.updateProduct(p);

        return Response.status(Status.OK).build();

    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") final Long id) {

        productRepository.removeProduct(id);

        return Response.status(Status.NO_CONTENT).build();
    }
    
    
    @GET
    @Path("/photos/{id}")
    public ExternalProduct getCatProductsById(@PathParam("id") final Long id) {

    	Product prod = productRepository.getProductById(id);

    	ExternalProduct externalProd = ExternalProduct.of(id, prod.getName(), prod.getDescription(), prod.photos);

        return externalProd;
    }
    
    @POST
    @Path("/{id}/photos/")
    public Response create(@PathParam("id") final Long id, final ExternalPhoto externalPhoto) {

        Photo ph = new Photo(externalPhoto.isFront(),externalPhoto.getName());
        
        Product product = productRepository.getProductById(id);
        
        product.photos.add(ph);
        
        productRepository.updateProduct(product);

        return Response.ok(product).build();
    }
   

}
