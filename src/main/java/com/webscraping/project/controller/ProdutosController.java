package com.webscraping.project.controller;



import com.webscraping.project.product.Produtos;
import com.webscraping.project.repository.ProdutosRepository;
import com.webscraping.project.services.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("busca")
public class ProdutosController {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private ProdutosService produtosService;



    @PostMapping("/scrape/{nomeProduto}")
    public ResponseEntity iniciarBusca(@PathVariable String nomeProduto){
        produtosService.buscarProdutos(nomeProduto);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/scrape/listarprodutos")
    public ResponseEntity<List<Produtos>> listarProdutos(){
        List<Produtos> produtos = produtosRepository.findAll();
        if (!produtos.isEmpty()) {
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
