# Web Scraping com Selenium, Java e Spring Boot

### O projeto surgiu quando eu precisava de novos periféricos para meu computador, porém, precisava que eles estivessem em promoção.
### Então, tive a ideia de fazer um Web Scraping utilizando Java para criar a lógica de negócio,  Selenium e WebDriver para a automação do processo de busca e coleta de dados da plataforma de vendas e o Spring Boot cuida de armazenar todos os produtos em promoção num banco de dados com o nome, preço e link de cada um para que eu não precisasse olhar produto por produto no site.

#### ** O selenium auxilia no web scraping permitindo fazer uma busca automatizada através de um WebDriver **

#### ** O WebDriver escolhido para auxiliar o processo foi o Edge WebDriver **

#  Utilizando 

### 1. Você irá rodar a classe DemoApplication normalmente.
### 2. Será necessário criar um usuário em https://localhost:8080/register.html utilizando o formato Login, Senha e Role, exemplo: Login: Teste123 - Senha: 123 - Role: ADMIN (É necessário a Role estar maiúscula e ser um admin).
### 3. Após o cadastro efetuado, siga para https://localhost:8080/login.html e faça o login com os dados criados.
### 4. Uma mensagem de Login Bem-Sucesido irá aparecer na tela junto com um token (o token é mostrado na tela, porém, não é uma boa prática, fiz apenas para capturar o token de forma rápida e realizar a requisição com um Bearer Token). Fiz uma página html simples sem CSS apenas para ter algo mais visual e rápido.
### 5. Utilizando a sua ferramente de preferência (Insomnia ou Postman), você pode realizar uma requisição post através da url https://localhost:8080/scrape/nome-do-produto/numero-de-paginas, exemplo: https://localhost:8080/scrape/memoria-ram ou https://localhost:8080/scrape/memoria ram. Não há problema caso haja espaço no nome do produto, a busca será realizada de forma correta.
### 6. O produto escolhido na requisição, caso em promoção, será armazenado no banco de dados no formato Id, Nome, Preço, Url. Em numero-de-paginas, você pode escolher o número de páginas a serem verificadas, por exemplo, https://localhost:8080/scrape/celular/2. 
### 7. Caso queira recebê-los através de um JSON na sua ferramenta (Insomnia ou Postman) sem precisar acessar o banco de dados e realizar um select de forma manual, faça uma requisição GET através da url https://localhost:8080/scrape/listarprodutos ou você pode querer procurar algo específico que esteja armazenado, então, pode fazer uma requisição GET na url http://localhost:8080/busca/scrape/nome-do-produto.
### ** Você pode alterar a url que deseja buscar o produto desejado, porém, terá que encontrar as classes dentro do html da página **
### ** As configurações de busca ficam dentro do ProdutosService **
