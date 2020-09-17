### Stack
- Java
- Maven
- Spring Boot
- Apache POI
- Liquibase 
- H2

### O que é ?
Dois Endpoints para download de planilha EXCEL.
Um endpoint faz download do arquivo físico, o outro faz download do arquivo em Base64

### Como rodar ?
Execute o comando **`mvn spring-boot:run`**, e consuma algum endpoint.
Ao rodar o projeto, irá criar automaticamente uma base de dados, em um banco H2 (via liquibase), inicializando com dados fakes de times de futebol, para gerar os relatórios. 

> **GET** http://localhost:8080/times/download/excel    
![](https://github.com/lucianoortizsilva/java-download-excel/blob/master/src/main/resources/static/github/download-excel.jpg)

> **GET** http://localhost:8080/times/download/base64
![](https://github.com/lucianoortizsilva/java-download-excel/blob/master/src/main/resources/static/github/download-base64.jpg)
