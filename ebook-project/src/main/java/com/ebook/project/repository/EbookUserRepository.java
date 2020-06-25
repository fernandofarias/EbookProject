package com.ebook.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ebook.project.model.EbookUser;

@Repository
public interface EbookUserRepository extends CrudRepository<EbookUser, Integer>  {
	
	public EbookUser findByLogin(String login);
	
	public EbookUser findByLoginAndSenha(String login, String senha);
	

}
