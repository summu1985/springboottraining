package com.sanaari.springboot.training.crmwebapp.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sanaari.springboot.training.crmwebapp.domain.Product;
import com.sanaari.springboot.training.crmwebapp.domain.ProductApiResponse;
import com.sanaari.springboot.training.crmwebapp.service.ProductAPIService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v4")
@Api(value = "onlinestore", description = "Product operations for Online Store")
public class ProductAPIController {
	@Autowired
	private ProductAPIService prodService;
	
	@Autowired
    private JobLauncher jobLauncher;
 
    @Autowired
    private Job processJob;
    
    @Autowired
    private Job fileProcessJob;

	// Get All Products
	@ApiOperation(value = "View a list of available products", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json")
	public List<Product> getAllProducts(HttpServletRequest req, HttpServletResponse res) {
		if (req.getHeader("Test") != null) {
			res.addHeader("Test", req.getHeader("Test"));
		}
		return (List<Product>) prodService.getAllProducts();
	}

	// Get the product details by
	// ID
	/* @CrossOrigin(origins = "http://localhost:9090") */
	@ApiOperation(value = "View a product", response = Product.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved product"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = "application/json")
	public Optional<Product> getProductById(@PathVariable(value = "id") int id) {
		return prodService.getProductById(id);
	}

	// Add a product
	@ApiOperation(value = "Add a product")
	@RequestMapping(value = "/product", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<ProductApiResponse> addProduct(@RequestBody Product product) {
		Product addedProduct = prodService.addProduct(product);
		
		if (addedProduct != null) {
			//return ResponseEntity.status().body();
			return new ResponseEntity<ProductApiResponse>(new ProductApiResponse("Successfully added product","success",addedProduct.getProductId()),HttpStatus.CREATED);
		} else {
			//return ResponseEntity.status().body();
			return new ResponseEntity<ProductApiResponse>(new ProductApiResponse("Could not add product !","failure",-1),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a product
	@ApiOperation(value = "Delete a product")
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity deleteProductById(@PathVariable(value = "id") int id) {
		int deleteResult = prodService.deleteProduct(id);
		ResponseEntity response;
		switch (deleteResult) {
		case -1:
			response = new ResponseEntity("Product id " + id + " could not be found.", HttpStatus.NOT_FOUND);
			break;
		case 0:
			response = new ResponseEntity("Product id " + id + " deleted successfully.", HttpStatus.OK);
			break;
		default:
			response = new ResponseEntity("Something went wrong while processing Product id " + id,
					HttpStatus.INTERNAL_SERVER_ERROR);
			break;
		}
		return response;
	}

	// Update a product
	@ApiOperation(value = "Update a product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated product"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity updateProductById(@PathVariable(value = "id") int id, @RequestBody Product product) {
		int productUpdated = prodService.updateProduct(id, product);
		ResponseEntity response;
		switch (productUpdated) {
		case -1:
			response = new ResponseEntity("Product id " + id + " could not be found.", HttpStatus.NOT_FOUND);
			break;
		case 0:
			response = new ResponseEntity("Product id " + id + " updated successfully.", HttpStatus.OK);
			break;
		default:
			response = new ResponseEntity("Something went wrong while processing Product id " + id,
					HttpStatus.INTERNAL_SERVER_ERROR);
			break;
		}
		return response;
	}
	
	@RequestMapping("/invokejob")
    public String handle() throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(processJob, jobParameters);
 
        return "Batch job has been invoked";
    }
	
	@RequestMapping("/invokeFilejob")
    public String handleFeedFile() throws Exception {
 
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(fileProcessJob, jobParameters);
 
        return "Feed file processing batch job has been invoked";
    }
}
