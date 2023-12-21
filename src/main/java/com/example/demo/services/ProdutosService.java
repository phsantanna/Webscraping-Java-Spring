package com.example.demo.services;

import com.example.demo.product.Produtos;
import com.example.demo.product.ProdutosDTO;
import com.example.demo.repository.ProdutosRepository;
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
        // Especifique o caminho completo para o msedgedriver.exe
        System.setProperty("webdriver.edge.driver", "C:\\Users\\psant\\OneDrive\\Área de Trabalho\\demo1\\edgedriver\\msedgedriver.exe");

        // Inicializar o WebDriver
        WebDriver driver = new EdgeDriver();

        try {
            String url = "https://www.kabum.com.br/busca/" + nomeProduto;
            driver.get(url);

            // Esperar um pouco para garantir que a página foi carregada
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".productCard")));

            // Encontrar todos os elementos de nameCard
            for (WebElement nameCard : driver.findElements(By.cssSelector(".productCard"))) {
                try {
                    // Verificar se a classe ".oldPriceCard" está presente na div
                    boolean hasOldPrice = !nameCard.findElements(By.cssSelector(".oldPriceCard")).isEmpty();

                    if (hasOldPrice) {
                        // Verificar se a div .oldPriceCard contém algum texto (um preço antigo)
                        WebElement oldPriceElement = nameCard.findElement(By.cssSelector(".oldPriceCard"));

                        if (!oldPriceElement.getText().isEmpty()) {
                            // A div .oldPriceCard tem um preço antigo, armazenar as informações do produto
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
            // Fechar o navegador
            driver.quit();
        }
    }
}
