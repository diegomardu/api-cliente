package io.github.diegomardu.rest.dto;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoPrestadoDto {
	
	@NotEmpty(message = "O campo descrição é obrigatório")
	private String descricao;
	
	@NotEmpty(message ="O campo preço é obrigatório")
	private String preco;
	
	@NotEmpty(message ="O campo data é obrigatório")
	private String data;
	
	@NotNull(message = "O campo cliente é obrigatŕio")
	private Integer idCliente;
	

}
