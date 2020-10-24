package com.dutra.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.dutra.cursomc.domain.Cliente;
import com.dutra.cursomc.domain.dto.ClienteDTO;
import com.dutra.cursomc.domain.dto.ClienteNewDTO;
import com.dutra.cursomc.domain.enums.TipoCliente;
import com.dutra.cursomc.repositories.ClienteRepository;
import com.dutra.cursomc.resources.exceptions.FieldMessage;
import com.dutra.cursomc.services.validation.utils.BR;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> lista = new ArrayList<>();
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer idDto = Integer.valueOf(map.get("id"));
		Cliente cliente = repository.findByEmail(objDto.getEmail());
		if (cliente != null && !cliente.getId().equals(idDto)) {
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