package com.ebook.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ebook.project.Dto.EbookProjectGenericResponse;
import com.ebook.project.model.EbookUser;
import com.ebook.project.repository.EbookUserRepository;


/**
 * classe responsável por todos os serviços referentes a autenticação
 * LOGIN, CRIAR CONTA, RECUPERAR SENHA...
 * */
@Service
public class EbookProjectUserService {
	
	@Autowired
	EbookUserRepository rpUser;
	
	public EbookProjectGenericResponse login(String login, String senha) {
		
		if (rpUser.findByLoginAndSenha(login, senha) == null) {
			return retorno("Usuario ou Senha Incorreto!", HttpStatus.BAD_GATEWAY);
		}
		return retorno("Login efetuado com Sucesso!", HttpStatus.OK);
	}
	
	public EbookProjectGenericResponse cadastrarUsuario(String nome, String login,  String senha, String email) {
		EbookUser usuario = new EbookUser();
		usuario.setLogin(login);
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setEmail(email);

		try {
			// valido se já existe um cadastro com o login informado
			if (rpUser.findByLogin(login) != null) {
				return retorno("O usuario " + login + " já esta em uso!", HttpStatus.IM_USED);
			}
			rpUser.save(usuario);
			return retorno("Usuario " + login + " criado com Sucesso!", HttpStatus.OK);

		} catch (Exception e) {
			return retorno("Não foi possivel criar nova conta", HttpStatus.BAD_REQUEST);
		}
	}
	
	public EbookProjectGenericResponse editarUsuario(String nome, String login, String senha, String email) {
		// busco um usuario por login e senha
		EbookUser usuario = rpUser.findByLogin(login);

		// valido se já existe um cadastro com o login informado
		if (usuario != null) {
			usuario.setLogin(login);
			usuario.setNome(nome);
			usuario.setSenha(senha);
			usuario.setEmail(email);
			
			rpUser.save(usuario);
			
			return retorno("Usuario " + login + " alterado com Sucesso!", HttpStatus.OK);
			
		}
		return retorno("Não foi possivel alter a conta", HttpStatus.BAD_REQUEST);
	}
	
	public EbookProjectGenericResponse excluirUsuario(String login, String senha) {
		// busco um usuario por login e senha
		EbookUser usuario = rpUser.findByLoginAndSenha(login, senha);

		// valido se existe usuario
		if (usuario != null) {
			rpUser.deleteById(usuario.getId());

			return retorno("Usuario excluido com Sucesso!", HttpStatus.OK);
		}
		return retorno("Não foi possivel excluir nova conta", HttpStatus.BAD_REQUEST);
	}
	/**
	 * Este mé todo é responsavel por montar o objeto de retorno com mensagem e codigo http
	 * */
	public EbookProjectGenericResponse retorno(String msg, HttpStatus status) {
		EbookProjectGenericResponse retorno = new EbookProjectGenericResponse();
		retorno.setMensagem(msg);
		retorno.setStatuscod(status);
		
		return retorno;
	}
}
