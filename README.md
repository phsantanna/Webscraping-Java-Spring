# Web Scraping com Selenium, Java e Spring Boot

#### O projeto surgiu quando eu precisava de novos periféricos para meu computador, porém, precisava que eles estivessem em promoção.
#### Então, tive a ideia de fazer um Web Scraping utilizando Java para criar a lógica de negócio,  Selenium e WebDriver para a automação do processo de busca e coleta de dados da plataforma de vendas e o Spring Boot cuida de armazenar todos os produtos em promoção num banco de dados com o nome, preço e link de cada um para que eu não precisasse olhar produto por produto no site.

#### ** O selenium auxilia no web scraping permitindo fazer uma busca automatizada através de um WebDriver **

#### ** O WebDriver escolhido para auxiliar o processo foi o Edge WebDriver **

#  Utilizando 

#### 1. Você irá rodar a classe DemoApplication normalmente.
#### 2. Será necessário criar um usuário em https://localhost:8080/register.html utilizando o formato Login, Senha e Role, exemplo: Login: Teste123 - Senha: 123 - Role: ADMIN (É necessário a Role estar maiúscula e ser um admin).
#### 3. Após o cadastro efetuado, siga para https://localhost:8080/login.html e faça o login com os dados criados.
#### 4. Uma mensagem de Login Bem-Sucesido irá aparecer na tela junto com um token (o token é mostrado na tela, porém, não é uma boa prática, fiz apenas para capturar o token de forma rápida e realizar o método com um Bearer Token). Fiz uma página html simples sem CSS apenas para ter algo mais visual e rápido.
#### 5. Utilizando a sua ferramenta de preferência (Insomnia ou Postman), você pode realizar um método POST através da url https://localhost:8080/nome-do-produto/numero-de-paginas, exemplo: https://localhost:8080/busca/memoria-ram/2 ou https://localhost:8080/busca/memoria ram/2. Não há problema caso haja espaço no nome do produto, a busca será realizada de forma correta.
#### 6. O produto escolhido no método, caso em promoção, será armazenado no banco de dados no formato Id, Nome, Preço, Url. Em numero-de-paginas, você pode escolher o número de páginas a serem verificadas, por exemplo, https://localhost:8080/busca/celular/2. 
#### 7. Caso queira recebê-los através de um JSON na sua ferramenta (Insomnia ou Postman) sem precisar acessar o banco de dados e realizar um select de forma manual, faça um método GET através da url https://localhost:8080/busca/listarprodutos ou você pode querer procurar algo específico que esteja armazenado, então, pode fazer um método GET na url http://localhost:8080/busca/nome-do-produto.
#### 8. Para deletar produtos específicos do BD faça um método DELETE https://localhost:8080/busca/produtos/deletar/{id} ou deletar todos com https://localhost:8080/busca/produtos/deletartodos.
#### ** Você pode alterar a url do site buscado para algum de sua preferência, porém, terá que encontrar as classes dentro do html da página e substituir as que estão em ProdutosService**
#### ** As configurações de busca ficam dentro do ProdutosService **
