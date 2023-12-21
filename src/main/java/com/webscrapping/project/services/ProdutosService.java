package com.webscrapping.project.services;

import com.webscrapping.project.product.Produtos;
import com.webscrapping.project.repository.ProdutosRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;

    public void buscarProdutos(String nomeProduto) {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\psant\\OneDrive\\Área de Trabalho\\demo1\\edgedriver\\msedgedriver.exe");

        WebDriver driver = new EdgeDriver();

        try {
            String url = "https://www.kabum.com.br/busca/" + nomeProduto;
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".productCard")));

            for (WebElement nameCard : driver.findElements(By.cssSelector(".productCard"))) {
                try {
                    boolean hasOldPrice = !nameCard.findElements(By.cssSelector(".oldPriceCard")).isEmpty();

                    if (hasOldPrice) {
                        WebElement oldPriceElement = nameCard.findElement(By.cssSelector(".oldPriceCard"));

                        if (!oldPriceElement.getText().isEmpty()) {
                            WebElement linkElement = nameCard.findElement(By.cssSelector("a.productLink"));
                            String produtoLink = linkElement.getAttribute("href");

                            String produtoNome = nameCard.findElement(By.cssSelector(".nameCard")).getText();
                            String precoProduto = nameCard.findElement(By.cssSelector(".priceCard")).getText();

                            System.out.println("Nome do Monitor: " + produtoNome);
                            System.out.println("Link do Produto: " + produtoLink);
                            System.out.println("Preço do Produto: " + precoProduto);
                            System.out.println("Em Promoção");
                            System.out.println();
                            Produtos produtos = new Produtos(produtoNome, precoProduto, produtoLink);
                            produtosRepository.save(produtos);
                        }
                    }
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    System.out.println("Div contendo o produto não encontrada");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
