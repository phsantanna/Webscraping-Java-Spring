package com.webscraping.project.services;

import com.webscraping.project.product.Produtos;
import com.webscraping.project.repository.ProdutosRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;

    private WebDriver driver;
    private WebDriverWait wait;

    public void setupWebDriver() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\psant\\OneDrive\\Área de Trabalho\\demo1\\edgedriver\\msedgedriver.exe");
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @PreDestroy
    public void quitWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void buscarProdutos(String nomeProduto, int numeroDaPagina) {
        try {
            setupWebDriver();
            navegarParaPaginaDeBusca(nomeProduto);
            processarPaginas(numeroDaPagina);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            quitWebDriver();
        }
    }

    private void navegarParaPaginaDeBusca(String nomeProduto) {
        String url = "https://www.kabum.com.br/busca/" + nomeProduto;
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".productCard")));
    }

    private void processarPaginas(int numeroDaPagina) {
        for (int i = 1; i <= numeroDaPagina; i++) {
            processarPaginaAtual();
            if (i < numeroDaPagina) {
                irParaProximaPagina();
            }
        }
    }

    private void processarPaginaAtual() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".productCard")));

        List<WebElement> productCards = driver.findElements(By.cssSelector(".productCard"));

        for (int i = 0; i < productCards.size(); i++) {
            try {
                WebElement nameCard = productCards.get(i);
                boolean hasOldPrice = !nameCard.findElements(By.cssSelector(".oldPriceCard")).isEmpty();

                if (hasOldPrice) {
                    WebElement oldPriceElement = nameCard.findElement(By.cssSelector(".oldPriceCard"));

                    if (!oldPriceElement.getText().isEmpty()) {
                        WebElement linkElement = nameCard.findElement(By.cssSelector("a.productLink"));
                        String produtoLink = linkElement.getAttribute("href");

                        String produtoNome = nameCard.findElement(By.cssSelector(".nameCard")).getText();
                        String precoProduto = nameCard.findElement(By.cssSelector(".priceCard")).getText();

                        // Verifica se o produto já existe no banco de dados antes de salvar
                        if (!produtoJaExiste(produtoNome, precoProduto, produtoLink)) {
                            System.out.println("Nome do Monitor: " + produtoNome);
                            System.out.println("Link do Produto: " + produtoLink);
                            System.out.println("Preço do Produto: " + precoProduto);
                            System.out.println("Em Promoção");
                            System.out.println();
                            Produtos produtos = new Produtos(produtoNome, precoProduto, produtoLink);
                            produtosRepository.save(produtos);
                        }
                    }
                }
            } catch (StaleElementReferenceException e) {
                // Elemento tornou-se obsoleto, atualize a lista de elementos e continue
                productCards = driver.findElements(By.cssSelector(".productCard"));
                i--;  // Reduza o índice para processar o mesmo índice novamente
            } catch (org.openqa.selenium.NoSuchElementException e) {
                System.out.println("Div contendo o produto não encontrada");
            }
        }
    }

    private void processarProduto(WebElement nameCard) {
        WebElement linkElement = nameCard.findElement(By.cssSelector("a.productLink"));
        String produtoLink = linkElement.getAttribute("href");

        String produtoNome = nameCard.findElement(By.cssSelector(".nameCard")).getText();
        String precoProduto = nameCard.findElement(By.cssSelector(".priceCard")).getText();

        System.out.println("Nome do Monitor: " + produtoNome);
        System.out.println("Link do Produto: " + produtoLink);
        System.out.println("Preço do Produto: " + precoProduto);
        System.out.println("Em Promoção");
        System.out.println();

        // Verifique se o produto já existe no banco de dados antes de salvar
        if (!produtoJaExiste(produtoNome, precoProduto, produtoLink)) {
            Produtos produtos = new Produtos(produtoNome, precoProduto, produtoLink);
            produtosRepository.save(produtos);
        }
    }

    private boolean produtoJaExiste(String produtoNome, String precoProduto, String produtoLink) {
        // Verifique se um produto com as mesmas características já existe no banco de dados
        Produtos existingProduct = produtosRepository.findByNomeProdutoAndPrecoProdutoAndUrlProduto(
                produtoNome, precoProduto, produtoLink);

        return existingProduct != null;
    }

    private void irParaProximaPagina() {
        WebElement nextPageButton = driver.findElement(By.cssSelector(".pagination .next"));
        nextPageButton.click();
    }
}