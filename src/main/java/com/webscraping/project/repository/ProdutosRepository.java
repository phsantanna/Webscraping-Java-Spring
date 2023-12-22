package com.webscraping.project.repository;

import com.webscraping.project.product.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {


    @Query(value = "SELECT * FROM tb_produtos WHERE unaccent(lower(nome_produto)) LIKE unaccent(lower(concat('%', :nomeProduto, '%')))", nativeQuery = true)
    List<Produtos> findByNomeContainingIgnoreCase(String nomeProduto);

    Produtos findByNomeProdutoAndPrecoProdutoAndUrlProduto(String produtoNome, String precoProduto, String produtoLink);
}
