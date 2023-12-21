package com.webscrapping.project.product;
import jakarta.persistence.*;
import lombok.*;


@Table(name="tb_produtos")
@Entity(name="produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private String precoProduto;
    private String urlProduto;

    public Produtos(String produtoNome, String precoProduto, String produtoLink) {
        this.nomeProduto = produtoNome;
        this.precoProduto = precoProduto;
        this.urlProduto = produtoLink;
    }
}
