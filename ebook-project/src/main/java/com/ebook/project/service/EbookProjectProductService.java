package com.ebook.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ebook.project.Dto.EbookProjectGenericResponse;
import com.ebook.project.model.EbookProduct;
import com.ebook.project.repository.EbookProductRepository;

@Service
public class EbookProjectProductService {
	
	@Autowired
	EbookProductRepository rpProduct;
	
	public List<EbookProduct> cadastrarProduto(String descricao, Double preco) {
		List<EbookProduct> listProducts = new ArrayList<EbookProduct>();
		EbookProduct product = new EbookProduct();
		product.setDescricao(descricao);
		product.setPreco(preco);
		
		product = rpProduct.saveAndFlush(product);
		
		listProducts.add(product);
		return listProducts;
	}
	
	public EbookProjectGenericResponse editarProduto(Integer id, String descricao, Double preco) {
		Optional<EbookProduct> product = rpProduct.findById(id);
		
		if (!product.isEmpty()) {
			EbookProduct produto = new EbookProduct(id, descricao, preco);
			rpProduct.save(produto);
			
			return retorno("Produto Cod.:" + id + "alterado com sucesso!", HttpStatus.OK);
		}
		return retorno("Não foi possivel alterar o produto!", HttpStatus.ALREADY_REPORTED);
	}
	
	public EbookProjectGenericResponse excluirProduto(Integer id) {
		Optional<EbookProduct> product = rpProduct.findById(id);

		if (!product.isEmpty()) {
			rpProduct.deleteById(id);
			return retorno("Produto Cod.:" + id + "excluido com sucesso!", HttpStatus.OK);
		}
		return retorno("Não foi possivel excluir o produto!", HttpStatus.ALREADY_REPORTED);
	}
	
	public List<EbookProduct> consultarTodos() {
		List<EbookProduct> listProducts = rpProduct.findAll();
		
		return listProducts;
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
