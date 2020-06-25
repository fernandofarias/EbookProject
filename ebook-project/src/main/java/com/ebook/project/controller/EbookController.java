package com.ebook.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebook.project.Dto.EbookProjectGenericResponse;
import com.ebook.project.model.EbookProduct;
import com.ebook.project.service.EbookProjectProductService;
import com.ebook.project.service.EbookProjectUserService;

@RestController
@RequestMapping(value = "/EbookProject")
public class EbookController {
	
	@Autowired
	EbookProjectUserService ebookProjectUserService;
	
	@Autowired
	EbookProjectProductService ebookProjectProductService;
	
	@PostMapping(value = "User/Cadastrar")
	public EbookProjectGenericResponse cadastrarUsuario(@Param(value = "nome")  String nome,
												        @Param(value = "login") String login,
											            @Param(value = "senha") String senha,
											            @Param(value = "email") String email) {
		return ebookProjectUserService.cadastrarUsuario(nome, login, senha, email);
	}
	
	@PutMapping(value = "User/Editar")
	public EbookProjectGenericResponse editarUsuario(@Param(value = "nome")  String nome,
												     @Param(value = "login") String login,
											         @Param(value = "senha") String senha,
											         @Param(value = "email") String email) {
		return ebookProjectUserService.editarUsuario(nome, login, senha, email);
	}
	
	@DeleteMapping(value = "User/Excluir")
	public EbookProjectGenericResponse excluirUsuario(@Param(value = "login") String login,
											          @Param(value = "senha") String senha) {
		return ebookProjectUserService.excluirUsuario(login, senha);
	}
	
	@PostMapping(value = "User/Login")
	public EbookProjectGenericResponse login(@Param(value = "login") String login,
										     @Param(value = "senha") String senha) {
		return ebookProjectUserService.login(login, senha);
	}
	
	@PostMapping(value = "Product/Cadastrar")
	public List<EbookProduct> cadastrarProduto(@Param(value = "descricao") String descricao,
											   @Param(value = "preco")     Double preco) {
		return ebookProjectProductService.cadastrarProduto(descricao, preco);
	}

	@GetMapping(value = "Product/ConsultarTodos")
	public List<EbookProduct> consultarTodos() {
		return ebookProjectProductService.consultarTodos();
	}
	
	@PutMapping(value = "Product/Editar")
	public EbookProjectGenericResponse editarProduto(@Param(value = "id")        Integer id,
											         @Param(value = "descricao") String descricao,
											         @Param(value = "preco")     Double preco) {
		return ebookProjectProductService.editarProduto(id, descricao, preco);
	}
	
	@DeleteMapping(value = "Product/Excluir")
	public EbookProjectGenericResponse excluirProduto(@Param(value = "id") Integer id) {
		return ebookProjectProductService.excluirProduto(id);
	}
}
