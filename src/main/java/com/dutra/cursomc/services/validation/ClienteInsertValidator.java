package com.dutra.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.dutra.cursomc.domain.Cliente;
import com.dutra.cursomc.domain.dto.ClienteNewDTO;
import com.dutra.cursomc.domain.enums.TipoCliente;
import com.dutra.cursomc.repositories.ClienteRepository;
import com.dutra.cursomc.resources.exceptions.FieldMessage;
import com.dutra.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> lista = new ArrayList<>();
		if (objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo()) && !BR.isValidCpf(objDto.getCpfOuCnpj())) {
			lista.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		} else if (objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) && !BR.isValidCnpj(objDto.getCpfOuCnpj())) {
			lista.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		Cliente cliente = repository.findByEmail(objDto.getEmail());
		if (cliente != null) {
			lista.add(new FieldMessage("email", "E-mail existente"));
		}
		for (FieldMessage e : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return lista.isEmpty();
	}
}