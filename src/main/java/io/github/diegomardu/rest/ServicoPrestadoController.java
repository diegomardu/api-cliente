package io.github.diegomardu.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.diegomardu.model.entity.Cliente;
import io.github.diegomardu.model.entity.ServicoPrestado;
import io.github.diegomardu.model.repository.ClienteRepository;
import io.github.diegomardu.model.repository.ServicoPrestadoRepository;
import io.github.diegomardu.rest.dto.ServicoPrestadoDto;
import io.github.diegomardu.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servico-prestado")
@RequiredArgsConstructor
public class ServicoPrestadoController {

	
	private final ServicoPrestadoRepository servicoPrestadoRepository;
	private final ClienteRepository clienteRepository;
	private final BigDecimalConverter bigDecimalConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDto dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();

		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));

		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.conversor(dto.getPreco()));

		return servicoPrestadoRepository.save(servicoPrestado);

	}
	
	@GetMapping
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false, defaultValue = "")String nome,
			@RequestParam(value = "mes" ,required = false)Integer mes
			){
		return servicoPrestadoRepository.findByNomeClienteAndMes("%" +nome + "%",mes);
		
	}

}
