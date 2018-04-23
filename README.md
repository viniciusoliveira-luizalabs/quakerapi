# Quake log parser
## Por que Java?
Pelo conhecimento pré-adquirido, simplicidade e pela performance sobre outras linguagens.  
(https://www.infoworld.com/article/2883328/node-js/java-vs-nodejs-an-epic-battle-for-developer-mindshare.html)  
(https://www.vividcortex.com/blog/2013/12/09/analysis-of-paypals-node-vs-java-benchmarks/)  

## Requisitos
Java JDK 8 http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html  
Maven  http://maven.apache.org/download.cgi  

## Instalação
Java - Seguir instruções em https://www.devmedia.com.br/preparacao-do-ambiente-para-desenvolvimento-em-java/25188   
Maven - Seguir instruções em http://luizricardo.org/2014/06/instalando-configurando-e-usando-o-maven-para-gerenciar-suas-dependencias-e-seus-projetos-java/ 
A partir da raiz do projeto rodar o comando (cmd): ```mvn clean install``` 

## API
Para executar, utilizando o console (cmd):  
- ``` cd <caminho do projeto> ```
- ``` mvn spring-boot:run ```

## Informações
A API possui objetivo de ler um arquivo de log do jogo Quake e disponibilizar as informações colhidas de forma legível.  
- Porta utilizada 8199 (http://localhost:8199)  
- Endpoints  
  -> "/api/v1/game/{numero}" retorna informações de um jogo especifico  
  -> "/api/v1/games" retorna informações sobre todos os jogos  

## Testes unitários
A partir da raiz do projeto rodar o comando (cmd): mvn test  
