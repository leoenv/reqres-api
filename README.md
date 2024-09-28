## Tecnologias utilizadas no projeto

- Java JDK 8 - [Java JDK 8](https://www.oracle.com/br/java/technologies/javase/javase8-archive-downloads.html)
- IDE de desenvolvimento (Sugestão) - [JetBrains IntelliJ IDEA Community](https://www.jetbrains.com/pt-br/idea/download/#section=windows)
- Framework de testes automatizados de API - [RestAssured](https://rest-assured.io/)
- Relatório de teste - [Extent Reports](https://www.extentreports.com/)
- Orquestrador de testes - [TestNG](https://testng.org/doc/)
- Facilitador de criação de objetos Java - [Lombok](https://projectlombok.org/)

## Abrindo o projeto (Baseado no uso do IntelliJ)

1. Baixar o projeto
2. Abrir IntelliJ 
3. Clicar em "File"
4. Clicar em "Open"
5. Selecinar a pasta raiz do projeto
6. Clicar em "OK"
7. O projeto será carregado e as dependências serão baixadas automaticamente. Aguardar até o fim.

## Arquitetura:

A arquitetura padrão composta por:

- `bases`: Pacote que contém as classes pai, que definem comportamentos ou contém implementações que serão extendidas pelas classes filhas. As já existentes são:
  - **RequestRestBase:** Deve ser herdada por toda classe "request" que representará uma requisição de uma API REST
  - **TestBase:** Deve ser herdada por toda classe "test".
- `jsonObjects`: Paconte que contém as classes que representam os objetos json que serão utilizados para serializar os contratos (body) das requisições;
- `requests`: Pacote que contém as classes que representam as requisições (endpoints ou serviços) da API que será testada;
- `tests`: Pacote de classes que contém os métodos de teste;
- `utils`: Pacote que contém classes com utilitários das teconlogias utilizadas no template, as já existentes são:
  - **ExtentReportsUtils:** Contém os métodos que implementam o relatório de testes.
  - **GeneralUtils:** Contém métodos que podem ser utilizados em qualquer ponto do código e não estão ligados à uma tecnologia específica, ex: métodos para formatar json, métodos para manipulação de datas, métodos para gerar cpf etc.
  - **RestAssuredUtils:** Contém os métodos que são o motor de execução das requisições através do RestAssured
- `GlobalParameters.java`: Classe que representa os parâmetros globais do projeto;
- `globalParameters.properties`: Arquivo de configuração dos parâmetros globais do projeto;
- `pom.xml`: Arquivo de configuração do Maven, utilizado para incluir bibliotecas no projeto e definir os procedimentos de build;

## Configuração das variáveis globais/de ambiente dos testes:
O arquivo globalParameters.properties contém as variáveis globais e de ambientes utilziadas pelos testes.
É um arquivo simples de chave e valor (chave=valor) que é um padrão de aplicações desenvolvidas em Java.

## Estrutura dos testes:
Os testes foram estruturados utilizando a ideia do Padrão AAA (Arranjo, Ação e Asserção), onde:
- **Arranjo:** Parâmetros do teste, configurações de serialização (se for o caso), instâcia da classe de requisição e configuração da requisição.
- **Ação:** Execução da request
- **Assert:** Passos de asserção

## Relatório de testes:
Sempre que um ou mais testes são executados um relatório da execução é gerado na pasta indicada na variável "report.path" do arquivo "globalParameters.properties" (valor default é target/reports).
Para abrir o relatório, basta navegar para a pasta que contém o realatório da execução e abrir (através do browser) o arquivo .html com o nome do relatório

## Padrão escrita de código:
O padrão que foi adotado para escrita é o “CamelCase” onde uma palavra é separada da outra através de letras maiúsculas. Este padrão é adotado para o nome de classes, métodos, variáveis e arquivos em geral exceto constantes. Constantes são escritas com todas suas letras em maiúsculo separando as palavras com “_”.

Ex: setJsonBody(), nomeUsuario, GetTokenRequest etc.
Ex. constantes: URL_DEFAULT.

- **Pacotes:** sempre escritos com todas as palavras em letra minúscula sem separação
- **Classes:** começam sempre com letra maiúscula
- **Arquivos de classes:** começam sempre com letra maiúscula
- **Arquivos que não são de classes:** começam sempre com letra minúscula
- **Métodos:** começam sempre com letra minúscula
- **Variáveis:** começam sempre com letra minúscula
- **Variáveis de Objetos:** começam sempre com letra minúscula





