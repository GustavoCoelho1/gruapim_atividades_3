# gruapim_atividades_3
Atividades referentes a 3a aula de APIs e Microservicoes

### Resposta exercício 3:
1. **Qual a principal diferença entre REST e SOAP?**<br><br>
   A principal diferença está na arquitetura. O SOAP é um protocolo altamente padronizado e rígido que utiliza exclusivamente XML para formatação de mensagens, dependendo de contratos estritos (WSDL). O REST não é um protocolo, mas um estilo arquitetural mais leve e flexível que utiliza os padrões da web (HTTP), permitindo múltiplos formatos de dados, sendo o JSON o mais comum hoje em dia.


2. **Em quais cenários SOAP ainda é amplamente utilizado?**<br><br>
   O SOAP ainda é forte em sistemas corporativos legados, bancos, telecomunicações e integrações governamentais. Isso acontece porque esses cenários exigem confiabilidade e contratos rigorosos que evitem ambiguidades e segurança a nível de mensagem.


3. **Quais são as vantagens e desvantagens de usar REST ao invés de SOAP?**<br><br>
    Vantagens do REST: É mais rápido, consome menos banda (JSON é mais leve que XML), é mais fácil de implementar e aprender, altamente escalável e utiliza recursos nativos do HTTP (como cache).  Desvantagens do REST: Não possui um padrão oficial de segurança a nível de mensagem, não impõe contratos de forma nativa (embora existam ferramentas para contornar) e o gerenciamento de transações complexas em múltiplos serviços é mais difícil de orquestrar em comparação com o SOAP.


4. **O que é WS-Security e como ele se compara à segurança em APIs REST?**<br><br>
   O WS-Security é uma extensão do protocolo SOAP que aplica segurança diretamente na mensagem XML, garantindo que os dados permaneçam seguros mesmo se passarem por vários intermediários antes do destino final. Em contrapartida, as APIs REST dependem da segurança a nível de transporte (HTTPS/TLS) para proteger os dados em trânsito e utilizam tokens (como JWT ou OAuth2) para autenticação. Se a conexão HTTPS for interrompida no REST, a carga útil perde sua proteção inerente, diferentemente do WS-Security.


5. **Explique o modelo de maturidade de Richardson e em que nível SOAP se encaixa.**<br><br>O Modelo de Maturidade de Richardson avalia o quão "RESTful" é uma API, dividido em 4 níveis:

    1. Nível 0 (Swamp of Pox): Usa HTTP apenas como túnel, geralmente com um único endpoint (ex: /api) e um único método (POST) para tudo.

    2. Nível 1 (Recursos): Começa a dividir a API em múltiplos endpoints baseados em recursos (ex: /contatos, /enderecos), mas ainda usa o mesmo verbo HTTP para várias ações.

    3. Nível 2 (Verbos HTTP): Usa recursos e os verbos corretos do HTTP (GET para ler, POST para criar, DELETE para apagar) junto com os status codes corretos.

    4. Nível 3 (HATEOAS): A API se torna autodocumentada, retornando links (hipermídia) nas respostas informando o que o cliente pode fazer a seguir com aquele recurso.