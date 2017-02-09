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

import project.me.dao.CategoryDao;
import project.me.model.Category;
import project.me.model.Product;
import project.me.rest.endpoint.contracts.ExternalCategory;
import project.me.rest.endpoint.contracts.ExternalProduct;

@Stateless
@Path("category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {
	
	@Inject
	private CategoryDao categoryRepository;


    @GET
    public List<ExternalCategory> getAll() {
    	
    	List<Category> categorys = categoryRepository.getAllCategorys();
    	
		List<ExternalCategory> externalCategorys = new ArrayList<>( categorys.size() );

		for (Category cat : categorys ) {
			externalCategorys.add(ExternalCategory.of(cat.getId(), cat.getName()));
		}

		return externalCategorys;
    }

    @GET
    @Path("{id}")
    public ExternalCategory getById(@PathParam("id") final Long id) {

    	Category prod = categoryRepository.getCategoryById(id);

    	ExternalCategory externalProd = ExternalCategory.of(id, prod.getName());

        return externalProd;
    }

    @GET
    @Path("/products/{id}")
    public ExternalCategory getCatProductsById(@PathParam("id") final Long id) {

    	Category prod = categoryRepository.getCategoryById(id);

    	ExternalCategory externalProd = ExternalCategory.of(id, prod.getName(), prod.product);

        return externalProd;
    }
    
    @POST
    @Path("/{id}/products/")
    public Response create(@PathParam("id") final Long id, final ExternalProduct extProduct) {

        Product p = new Product(extProduct.getName(),extProduct.getDescription());
        
        Category cat = categoryRepository.getCategoryById(id);
        
        cat.product.add(p);
        
        categoryRepository.updateCategory(cat);

        return Response.ok(p).build();
    }
   
    
    
    @POST
    public Response create(@Valid final ExternalCategory extCat) {

        Category p = new Category(extCat.getName());
		p = categoryRepository.createCategory(p);

        return Response.ok(p).build();
    }
   
    
    

    @PUT
    public Response update(final ExternalCategory extCategory) {

        final Category p = new Category(extCategory.getName());
        
        categoryRepository.updateCategory(p);

        return Response.status(Status.OK).build();

    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") final Long id) {

        categoryRepository.removeCategory(id);

        return Response.status(Status.NO_CONTENT).build();
    }

}
