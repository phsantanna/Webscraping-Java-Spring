# Web Scraping com Selenium, Java e Spring Boot

O projeto teve início quando eu estava em busca de novos periféricos para meu computador, mas com a condição de que estivessem em promoção. Para facilitar essa tarefa, surgiu a ideia de utilizar Java para a lógica de negócio, Selenium e WebDriver para automação do processo de busca e coleta de dados de uma plataforma de vendas. O Spring Boot entra em cena para armazenar todos os produtos em promoção em um banco de dados, contendo informações como nome, preço e link. Isso elimina a necessidade de verificar cada produto individualmente no site.

O Selenium desempenha um papel crucial no web scraping, permitindo buscas automatizadas através de um WebDriver.

O WebDriver escolhido para auxiliar no processo foi o Edge WebDriver.

# Utilizando

1. Execute a classe `DemoApplication` como de costume.
2. Crie um usuário em https://localhost:8080/register.html utilizando o formato de login, senha e role. Exemplo: Login: Teste123 - Senha: 123 - Role: ADMIN (A role precisa estar em maiúsculas e ser um admin).
3. Após o cadastro, acesse https://localhost:8080/login.html e faça o login com os dados criados.
4. Uma mensagem de "Login Bem-Sucedido" será exibida junto com um token (a exibição do token na tela não é uma boa prática, foi feito apenas para capturar o token de forma rápida).
5. Utilizando sua ferramenta preferida (Insomnia ou Postman), realize um método POST na URL https://localhost:8080/nome-do-produto/numero-de-paginas. Exemplo: https://localhost:8080/busca/memoria-ram/2 ou https://localhost:8080/busca/memoria ram/2. Espaços no nome do produto não são problema, a busca será realizada corretamente.
6. O produto escolhido no método, se estiver em promoção, será armazenado no banco de dados no formato Id, Nome, Preço, Url. Em numero-de-paginas, você pode escolher o número de páginas a serem verificadas, por exemplo, https://localhost:8080/busca/celular/2.
7. Caso queira visualizar os produtos em formato JSON na sua ferramenta (Insomnia ou Postman) sem precisar acessar o banco de dados e realizar um select manualmente, faça um método GET na URL https://localhost:8080/busca/listarprodutos. Ou, para procurar algo específico armazenado, faça um método GET na URL http://localhost:8080/busca/nome-do-produto.
8. Para excluir produtos específicos do BD, faça um método DELETE em https://localhost:8080/busca/produtos/deletar/{id} ou para deletar todos, use https://localhost:8080/busca/produtos/deletartodos.
   
**Nota: Você pode alterar a URL do site buscado para algum de sua preferência, mas precisará encontrar as classes dentro do HTML da página e substituir as que estão em `ProdutosService`. As configurações de busca ficam dentro do `ProdutosService`.**
