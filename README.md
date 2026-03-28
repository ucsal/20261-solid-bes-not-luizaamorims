# Refatoração SOLID 

## O que foi feito

O código original tinha tudo dentro de uma única classe `App.java`: menu, validações, cálculo de nota, impressão do tabuleiro e os dados iniciais. A refatoração separou essas responsabilidades em classes menores, aplicando os princípios SOLID.


## Onde cada princípio foi aplicado

**S — Single Responsibility**
Cada classe faz apenas uma coisa. O `MenuConsole` só cuida do menu, o `CalculadoraNota` só calcula a nota, o `TabuleiroImprimir` só imprime o tabuleiro. Antes tudo isso estava misturado no `App.java`.

**O — Open/Closed**
Se quiser mudar a forma de calcular a nota (por exemplo, descontar ponto por erro), basta criar uma nova classe no lugar de `CalculadoraNota` sem precisar mexer nos outros serviços.

**L — Liskov Substitution**
As classes `Memory*` implementam interfaces. Isso significa que poderiam ser trocadas por outras implementações (como uma que salva em banco de dados) sem quebrar nada no resto do código.

**I — Interface Segregation**
Cada interface de repositório é pequena e específica. `ParticipanteRepository` só tem métodos de participante, `ProvaRepository` só tem métodos de prova, e assim por diante.

**D — Dependency Inversion**
Os serviços não criam seus próprios repositórios. Eles recebem os repositórios prontos pelo construtor. O `App.java` é o único lugar onde tudo é instanciado e conectado.


## Outras melhorias feitas

- **Substituição do `while(true)` por `boolean rodando`** — o loop do menu ficou mais legível, sem depender de `return` no meio do fluxo para encerrar.

- **Extração do método `coletarResposta`** — no original a coleta da resposta do usuário estava embutida dentro do método `aplicarProva`. Separar em método próprio deixou o código mais organizado e fácil de entender.

- **Validação de input vazio no `coletarResposta`** — além de tratar exceções, agora o código verifica se o usuário digitou algo antes de tentar processar a resposta.

- **Comentários nas classes** — cada classe tem um comentário explicando sua responsabilidade, facilitando a leitura e manutenção do código.

- **Apesar de não utilizarmos frameworks externos como o Spring Boot,
tentei seguir a mesma ideia de separação em camadas que ele propõe:
repository para acesso aos dados, service para a lógica de negócio
e uma camada de apresentação (MenuConsole). A injeção de dependência
também foi feita manualmente pelo construtor :)

