# Complexidade Ciclomática de McCabe

## 📖 Sobre o Projeto

Este projeto foi desenvolvido como atividade acadêmica para demonstrar a aplicação da métrica **Complexidade Ciclomática**, criada por Thomas J. McCabe em 1976.

A Complexidade Ciclomática é uma métrica de engenharia de software utilizada para medir a quantidade de caminhos independentes existentes no fluxo de execução de uma função ou programa. Seu principal objetivo é auxiliar na avaliação da complexidade do código, na definição da cobertura mínima de testes e na identificação de trechos que podem exigir manutenção ou refatoração.

---

## 🎯 Objetivo

Demonstrar, de forma prática, o cálculo e a análise da Complexidade Ciclomática utilizando um exemplo simples em Python.

O projeto apresenta:

* Implementação de uma função com estruturas condicionais.
* Representação do fluxo de execução por meio de um grafo.
* Cálculo manual da Complexidade Ciclomática.
* Validação automática da métrica utilizando a ferramenta Lizard.

---

## 📂 Estrutura do Projeto

```text
ComplexidadeCiclomatica/
│
├── main.py
├── README.md
├── print-execucao.png
├── print-lizard.png
└── grafo-fluxo.png
```

---

## 💻 Código Utilizado

```python
def calcular_elegibilidade_seguro(
    idade_grupo,
    possui_sinistro,
    tipo_veiculo
):

    if idade_grupo == "Jovem":

        if possui_sinistro == "Sim":
            return "Recusado"

        return "Aprovado Risco Alto"

    if tipo_veiculo == "Utilitario":
        return "Aprovado Risco Medio"

    return "Aprovado Risco Padrao"
```

---

## 🔄 Grafo de Fluxo de Controle

```text
                [Início]
                    |
                    v
      idade_grupo == "Jovem" ?
             /           \
           Sim           Não
            |             |
            v             v
 possui_sinistro?   tipo_veiculo?
      /      \         /      \
    Sim      Não   Util.    Outro
     |         |      |        |
     v         v      v        v
Recusado   Aprovado  Médio   Padrão
      \        |       |        /
       \       |       |       /
               v
             [Fim]
```

---

## 📊 Cálculo da Complexidade Ciclomática

A função possui três estruturas condicionais:

1. `if idade_grupo == "Jovem"`
2. `if possui_sinistro == "Sim"`
3. `if tipo_veiculo == "Utilitario"`

Aplicando a regra prática:

```text
Complexidade = Número de Decisões + 1
```

Temos:

```text
Complexidade = 3 + 1
Complexidade = 4
```

Portanto, a função possui **4 caminhos independentes de execução**.

---

## 🧪 Execução do Projeto

Executar o programa:

```bash
python main.py
```

Saída esperada:

```text
Recusado
```

---

## 🔍 Análise com Lizard

Instalar a ferramenta:

```bash
pip install lizard
```

Executar a análise:

```bash
lizard main.py
```

Resultado esperado:

```text
================================================
NLOC    CCN   token PARAM length location
------------------------------------------------
13      4     46    3     17 calcular_elegibilidade_seguro
================================================
```

Onde:

```text
CCN = 4
```

CCN (Cyclomatic Complexity Number) representa a Complexidade Ciclomática da função analisada.

---

## ✅ Interpretação do Resultado

Segundo a classificação tradicional:

| Complexidade | Classificação |
| ------------ | ------------- |
| 1 – 10       | Baixa         |
| 11 – 20      | Moderada      |
| Acima de 20  | Crítica       |

O resultado obtido foi:

```text
CCN = 4
```

Portanto, a função é considerada de **baixa complexidade**, apresentando boa legibilidade e facilidade de manutenção.

---

## 🛠 Ferramentas Utilizadas

* Python
* Lizard
* GitHub

Ferramentas relacionadas à análise de Complexidade Ciclomática:

* SonarQube
* SonarCloud
* Radon
* Checkstyle
* PMD

---

## 📚 Referências

MCCABE, Thomas J. *A Complexity Measure*. IEEE Transactions on Software Engineering, v. SE-2, n. 4, p. 308–320, 1976.

MCCONNELL, Steve. *Code Complete*. 2. ed. Microsoft Press, 2004.

MARTIN, Robert C. *Clean Code: A Handbook of Agile Software Craftsmanship*. Prentice Hall, 2008.

Lizard - Code Complexity Analyzer. Disponível em: https://github.com/terryyin/lizard

SonarQube. Disponível em: https://www.sonarsource.com/products/sonarqube/
