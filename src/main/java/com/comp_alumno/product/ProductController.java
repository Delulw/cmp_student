package com.comp_alumno.product;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public Flux<Product> findAll() {
		return productService.findAll();
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<Product>> findById(@PathVariable Long id) {
		return productService.findById(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Mono<ResponseEntity<Product>> create(@RequestBody ProductRequest request) {
		return productService.create(request)
				.map(product -> {
					URI location = UriComponentsBuilder.fromPath("/api/products/{id}")
							.buildAndExpand(product.id())
							.toUri();
					return ResponseEntity.created(location).body(product);
				});
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<Product>> update(@PathVariable Long id, @RequestBody ProductRequest request) {
		return productService.update(id, request)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable Long id) {
		return productService.delete(id)
				.map(deleted -> deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build());
	}
}