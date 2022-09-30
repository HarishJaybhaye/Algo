package com.example.algo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ProductController {

	@Autowired
	private ProductServicrImpl productServicrImpl;

	@CrossOrigin
	@PostMapping(value = "/add")
	private ResponseEntity<Response> addproduct(@RequestBody Product product) throws Exception {
		productServicrImpl.addProduct(product);
		Response response = new Response();
		response.setMessage("Product Added Successfully");
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}


	@GetMapping(value = "/{productId}")
	public ResponseEntity<Response> getProduct(@PathVariable Long productId) throws Exception {
		Response response = new Response();
		response.setResult(productServicrImpl.getProductById(productId));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value="/{productId}")
	public ResponseEntity<Response> updateProduct(@RequestBody Product product, @PathVariable Long productId)
			throws Exception {
		Response response = new Response();
		response.setResult(productServicrImpl.updateProduct(product, productId));
		response.setMessage("Product updated successfully");
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value="/{productId}") 
	public ResponseEntity<Response>deleteProduct(@PathVariable Long productId)throws Exception {
		productServicrImpl.deleteById(productId);
		Response response = new Response();
		response.setMessage("product deleted");
		return ResponseEntity.ok(response);
	}

}