package com.webscrapping.project.repository;

import com.webscrapping.project.product.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

}
