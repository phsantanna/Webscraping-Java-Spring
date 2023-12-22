package com.webscraping.project.repository;

import com.webscraping.project.product.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {


    @Query("SELECT p FROM produtos p WHERE LOWER(p.nomeProduto) LIKE %:nomeproduto%")
    List<Produtos> findByNomeContainingIgnoreCase(String nomeproduto);


    Produtos findByNomeProdutoAndPrecoProdutoAndUrlProduto(String produtoNome, String precoProduto, String produtoLink);
}
