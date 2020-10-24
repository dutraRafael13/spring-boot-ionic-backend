package com.dutra.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dutra.cursomc.domain.Produto;
import com.dutra.cursomc.domain.dto.ProdutoDTO;
import com.dutra.cursomc.resources.utils.URL;
import com.dutra.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> fin(@PathVariable Integer id) {
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhasPorPagina,
			@RequestParam(value = "ordenarPor", defaultValue = "nome") String ordenaPor,
			@RequestParam(value = "Direcao", defaultValue = "ASC") String direcao) {
		Page<Produto> produtos = service.search(URL.decodeParametro(nome), URL.converteEmListaInteiro(categorias),
				pagina, linhasPorPagina, ordenaPor, direcao);
		Page<ProdutoDTO> clientesDTO = produtos.map(obj -> new ProdutoDTO(obj)); 
		return ResponseEntity.ok().body(clientesDTO);
	}
	
}