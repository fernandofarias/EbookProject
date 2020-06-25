package com.ebook.project.Dto;

import org.springframework.http.HttpStatus;

public class EbookProjectGenericResponse {
	
	private String mensagem;
	private HttpStatus statuscod;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public HttpStatus getStatuscod() {
		return statuscod;
	}
	public void setStatuscod(HttpStatus statuscod) {
		this.statuscod = statuscod;
	}
}
