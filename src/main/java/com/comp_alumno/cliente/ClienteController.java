package com.comp_alumno.cliente;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private final ClienteService service;

	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Cliente> crear(@Valid @RequestBody ClienteRequest request) {
		Cliente cliente = service.crear(request);
		return ResponseEntity.created(URI.create("/api/clientes/" + cliente.getId())).body(cliente);
	}

	@GetMapping
	public List<Cliente> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Cliente obtener(@PathVariable Long id) {
		return service.obtener(id);
	}

	@PutMapping("/{id}")
	public Cliente actualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequest request) {
		return service.actualizar(id, request);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
