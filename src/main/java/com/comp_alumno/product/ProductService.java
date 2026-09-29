package com.comp_alumno.product;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	private final ConcurrentHashMap<Long, Product> products = new ConcurrentHashMap<>();
	private final AtomicLong nextId = new AtomicLong(1);

	public Flux<Product> findAll() {
		return Flux.defer(() -> Flux.fromIterable(products.values()));
	}

	public Mono<Product> findById(Long id) {
		return Mono.fromSupplier(() -> products.get(id));
	}

	public Mono<Product> create(ProductRequest request) {
		return Mono.fromSupplier(() -> {
			long id = nextId.getAndIncrement();
			Product product = new Product(id, request.name(), request.description(), request.price());
			products.put(id, product);
			return product;
		});
	}

	public Mono<Product> update(Long id, ProductRequest request) {
		return Mono.fromSupplier(() -> products.computeIfPresent(id,
				(key, existing) -> new Product(id, request.name(), request.description(), request.price())));
	}

	public Mono<Boolean> delete(Long id) {
		return Mono.fromSupplier(() -> products.remove(id) != null);
	}
}