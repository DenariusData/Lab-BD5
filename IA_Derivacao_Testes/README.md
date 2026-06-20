# 🧠 Inteligência Artificial Aplicada à Derivação de Testes

Este projeto apresenta uma análise teórica e prática sobre a utilização de **Inteligência Artificial (IA)** na geração, derivação e execução de testes de software. Além da fundamentação conceitual, o repositório consolida a implementação prática de uma regra de elegibilidade de seguros e testes utilizando **Pairwise** para otimização de cenários combinatórios.

---

## 📈 Prós e Contras do Uso de IA em Testes

O avanço de Large Language Models (LLMs) e ferramentas baseadas em IA generativa revolucionou o ciclo de vida do desenvolvimento de software (SDLC), especialmente na área de Garantia de Qualidade (QA). No entanto, sua adoção exige um equilíbrio cuidadoso entre a produtividade gerada e a confiabilidade dos resultados.

| Aspecto | Descrição |
| :--- | :--- |
| **🚀 Ganho de Escala (Prós)** | **Geração Acelerada de Código**: Criação imediata de estruturas de testes (boilerplates, setups e mocks).<br>**Ampla Cobertura**: Capacidade de sugerir casos de borda e entradas que desenvolvedores humanos poderiam esquecer.<br>**Tradução de Requisitos**: Facilidade em converter regras descritas em linguagem natural (requisitos de negócio) diretamente em asserções lógicas.<br>**Refatoração Eficiente**: Identificação rápida de testes redundantes e sugestões para otimização do código de teste. |
| **⚠️ Alucinações & Riscos (Contras)** | **Asserções Falsas**: Geração de validações (`assert`) que parecem corretas mas assumem comportamentos incorretos da regra de negócio.<br>**Código Inexistente (Fake APIs)**: Importação de bibliotecas fictícias ou uso de métodos que não existem nas dependências instaladas.<br>**Falso Sentimento de Segurança**: Confiança cega nas suítes geradas por IA, o que pode deixar passar bugs críticos caso os cenários não passem por revisão humana.<br>**Mudanças Silenciosas**: LLMs podem alterar a semântica de regras de negócio complexas ao reescrever código de teste. |

### O Paradoxo da Escala vs. Alucinação
Enquanto a IA permite a criação de **centenas de testes em minutos** (ganho de escala sem precedentes), a taxa de **alucinações lógicas** exige que o desenvolvedor gaste tempo revisando e depurando o código gerado. Portanto, a abordagem mais segura é utilizar a IA como um **copiloto de suporte**, onde a validação final e a lógica de negócios são sempre revisadas por humanos.

---

## 🛠️ Ferramentas e Frameworks de IA para Testes

Diversas ferramentas utilizam Inteligência Artificial para automatizar e otimizar processos de teste em diferentes camadas da aplicação:

1. **CodiumAI (Qodo)**: Analisa o código-fonte em tempo real para sugerir cenários de testes unitários abrangentes, encontrar bugs no código de produção e gerar planos de teste interativos.
2. **Keploy**: Uma ferramenta que cria testes de integração e mocks automaticamente gravando o tráfego de rede e interações com banco de dados de uma aplicação em execução (API Testing).
3. **GitHub Copilot / Cursor / Gemini**: Assistentes de IA de propósito geral que agilizam a escrita de testes unitários através de preenchimento automático inteligente baseado no contexto do projeto.
4. **AllPairsPy (Combinatorial Testing)**: Embora não seja estritamente uma IA generativa, algoritmos de teste combinatório como o **Pairwise** são frequentemente acoplados aos fluxos gerados por IA para garantir que a suite de testes gerada seja otimizada e eficiente.

---

## 📦 Consolidação dos Entregáveis

Este repositório contém a implementação prática das regras de elegibilidade de seguro, testes unitários convencionais e testes baseados em **Pairwise** (onde combinamos todas as duplas possíveis de parâmetros para reduzir a quantidade de testes necessários mantendo uma cobertura excelente).

### Estrutura de Arquivos

*   [`main.py`](file:///c:/Users/Aline%20Ramos/Documents/Lab-BD5/IA_Derivacao_Testes/main.py): Contém a lógica de negócio `calcular_elegibilidade_seguro`.
*   [`test_main.py`](file:///c:/Users/Aline%20Ramos/Documents/Lab-BD5/IA_Derivacao_Testes/test_main.py): Testes unitários focados em cenários estáticos específicos.
*   [`test_pairwise.py`](file:///c:/Users/Aline%20Ramos/Documents/Lab-BD5/IA_Derivacao_Testes/test_pairwise.py): Teste combinatório que aplica a biblioteca `allpairspy` para avaliar cenários de forma otimizada.
*   [`requirements.txt`](file:///c:/Users/Aline%20Ramos/Documents/Lab-BD5/IA_Derivacao_Testes/requirements.txt): Gerenciador de dependências.

---

## 🚀 Como Executar o Projeto

Siga os passos abaixo para preparar o ambiente e rodar os testes em seu computador.

### 1. Pré-requisitos
Certifique-se de ter o **Python 3.8+** instalado.

### 2. Instalar Dependências
Navegue até a pasta do projeto e instale as dependências:
```bash
pip install -r requirements.txt
```

### 3. Executar os Testes Unitários (Pytest)
Para rodar a suíte convencional de testes unitários:
```bash
pytest test_main.py
```

### 4. Executar os Testes Combinatórios (Pairwise)
Para gerar e executar os cenários dinâmicos de Pairwise:
```bash
python test_pairwise.py
```

---

## 📊 Entendendo o Otimizador Pairwise (Caso Prático)

No arquivo [`test_pairwise.py`](file:///c:/Users/Aline%20Ramos/Documents/Lab-BD5/IA_Derivacao_Testes/test_pairwise.py), definimos os seguintes parâmetros de teste:
1.  **Idade**: `["Jovem (Abaixo 25)", "Adulto (25-60)", "Idoso (Mais 60)"]` (3 valores)
2.  **Sinistro**: `["Sim", "Nao"]` (2 valores)
3.  **Tipo de Veículo**: `["Passeio", "Utilitario", "Motocicleta"]` (3 valores)

*   **Combinação Completa (Cartesiana)**: $3 \times 2 \times 3 = 18$ cenários de teste necessários.
*   **Otimização Pairwise**: Reduz o total para apenas **9 cenários**, garantindo que *qualquer par* de valores entre as três variáveis seja testado ao menos uma vez. Isso gera um ganho de escala imediato de **50% de redução no tempo de execução dos testes**, mantendo alta taxa de detecção de falhas de integração de parâmetros.
