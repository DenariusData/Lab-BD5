# Pairwise Testing — Demonstração Acadêmica

Projeto Java/Maven que demonstra a técnica de **Pairwise Testing** (testes
combinatórios / "all-pairs"), provando que é possível reduzir drasticamente o
número de casos de teste **sem perder a capacidade de detectar bugs de
integração** entre parâmetros.

> **Resultado central:** de **27** combinações exaustivas para apenas **9**
> casos Pairwise (-66%), mantendo a cobertura de todos os pares.

---

## O que é Pairwise Testing?

Muitos defeitos de software não dependem de um único parâmetro, mas da
**combinação de dois** parâmetros específicos. O Pairwise parte dessa
observação: em vez de testar **todas** as combinações possíveis (explosão
combinatória), ele seleciona um conjunto mínimo de casos que garante que
**todo par de valores** apareça ao menos uma vez.

Neste exemplo temos 3 parâmetros com 3 valores cada:

| Parâmetro   | Valores                          |
|-------------|----------------------------------|
| `pagamento` | Cartao, Pix, Boleto              |
| `frete`     | Normal, Expresso, Economico      |
| `perfil`    | Novo, Recorrente, VIP            |

- **Exaustivo:** 3 × 3 × 3 = **27** casos.
- **Pairwise:** apenas **9** casos cobrem todos os pares.

---

## Estrutura do projeto

```
Pairwise_Testing/
├── pom.xml                         # Maven + JUnit 5 + Surefire
├── pict-model.txt                  # Modelo para o Microsoft PICT (gera os 9 casos)
├── README.md
└── src/
    ├── main/java/com/denariusdata/pairwise/
    │   └── CheckoutService.java     # Sistema sob teste (SUT) + bug proposital
    └── test/java/com/denariusdata/pairwise/
        ├── CheckoutPairwiseTest.java    # 9 casos Pairwise (o destaque)
        └── CheckoutExhaustiveTest.java  # 27 casos exaustivos (contraste)
```

---

## Como rodar

Pré-requisitos: **Java 17+** e **Maven**.

```bash
mvn test
```

Com `BUG_PROPOSITAL = false` (padrão), tudo fica **verde**:
9 testes Pairwise + 27 testes exaustivos, todos passando.

---

## Ligando/desligando o bug proposital

O "bug" fica controlado por uma constante em
[`CheckoutService.java`](src/main/java/com/denariusdata/pairwise/CheckoutService.java):

```java
static final boolean BUG_PROPOSITAL = false;
```

Quando `true`, o serviço soma `+999` ao total **somente** na combinação de
**dois** parâmetros: `pagamento = "Pix"` **e** `frete = "Expresso"`.

- `BUG_PROPOSITAL = false` → `mvn test` todo **verde**.
- `BUG_PROPOSITAL = true`  → o **Caso 5: Pix + Expresso + VIP** do teste
  Pairwise **falha**, provando que apenas 9 casos foram suficientes para
  detectar a falha de integração.

---

## Gerando os 9 casos com PICT

```bash
pict pict-model.txt
```

O [Microsoft PICT](https://github.com/microsoft/pict) produz exatamente os
9 casos usados em `CheckoutPairwiseTest`. Veja o arquivo
[`pict-model.txt`](pict-model.txt).
