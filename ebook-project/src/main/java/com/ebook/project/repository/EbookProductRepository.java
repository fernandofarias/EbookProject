package com.ebook.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebook.project.model.EbookProduct;

@Repository
public interface EbookProductRepository extends JpaRepository<EbookProduct, Integer>{

}
