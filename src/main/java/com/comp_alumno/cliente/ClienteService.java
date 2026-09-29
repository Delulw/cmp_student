package com.comp_alumno.cliente;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ClienteService {

	private final ClienteRepository repository;

	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}

	public Cliente crear(ClienteRequest request) {
		return repository.save(new Cliente(request.nombre(), request.email()));
	}

	@Transactional(readOnly = true)
	public List<Cliente> listar() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Cliente obtener(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
	}

	public Cliente actualizar(Long id, ClienteRequest request) {
		Cliente cliente = obtener(id);
		cliente.actualizar(request.nombre(), request.email());
		return repository.save(cliente);
	}

	public void eliminar(Long id) {
		if (!repository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
		}
		repository.deleteById(id);
	}
}
