package com.example.demo.product;

import jakarta.validation.constraints.NotBlank;

public record ProdutosDTO(@NotBlank String nome, @NotBlank String preco, @NotBlank String url) {
}
