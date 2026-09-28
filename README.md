# Sistema de Estoque de Produtos (Java)

Exercício de POO em Java: classes abstratas, herança, interfaces, polimorfismo
(dinâmico e estático), composição e hierarquia de exceções.

## Estrutura

```
src/
├── EstoqueException.java              # exceção base (checked)
├── QuantidadeInvalidaException.java   # extends EstoqueException
├── ProdutoIndisponivelException.java  # extends EstoqueException
├── Vendavel.java                      # interface: vender()
├── Product.java                       # classe abstrata (implements Vendavel)
├── ProdutoComum.java                  # extends Product
├── ProdutoPerecivel.java              # extends Product (20% off se vence em <= 3 dias)
├── Estoque.java                       # composição: TEM UMA lista de Product
└── EstoqueApp.java                    # main()
```

## Conceitos aplicados

| Conceito | Onde |
|---|---|
| Classe abstrata + método abstrato | `Product.calcularValorTotal()` |
| Encapsulamento | atributos `private` em `Product` |
| Herança + `@Override` | `ProdutoComum`, `ProdutoPerecivel` |
| Polimorfismo dinâmico | `Estoque.calcularValorTotalEstoque()` |
| Interface | `Vendavel` implementada por `Product` |
| Sobrecarga (polimorfismo estático) | `Product.aplicarDesconto(...)` (2 versões) |
| Composição | `Estoque` tem uma `List<Product>` |
| Hierarquia de exceções | `EstoqueException` → 2 filhas; catches em ordem (específicas → genérica) |

## Como compilar e executar

Requer JDK 11+.

```bash
mkdir out
javac -encoding UTF-8 -d out src/*.java
java -cp out EstoqueApp
```

## Saída esperada

Veja o arquivo [`saida.txt`](saida.txt).
