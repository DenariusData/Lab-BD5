package com.denariusdata.pairwise;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * TESTE EXAUSTIVO — serve apenas de CONTRASTE com o Pairwise.
 *
 * Gera e executa TODAS as 27 combinações (3 pagamentos x 3 fretes x 3 perfis)
 * através de 3 laços aninhados e @TestFactory.
 *
 * Compare no relatório do "mvn test":
 *   - Pairwise:  9 testes
 *   - Exaustivo: 27 testes
 * Mesma cobertura de pares, 3x mais casos. Em sistemas reais com muitos
 * parâmetros, a explosão combinatória torna o exaustivo inviável.
 */
class CheckoutExhaustiveTest {

    private final CheckoutService servico = new CheckoutService();

    private static final String[] PAGAMENTOS = {"Cartao", "Pix", "Boleto"};
    private static final String[] FRETES     = {"Normal", "Expresso", "Economico"};
    private static final String[] PERFIS     = {"Novo", "Recorrente", "VIP"};

    @TestFactory
    List<DynamicTest> todasAsCombinacoes() {
        List<DynamicTest> testes = new ArrayList<>();
        int contador = 0;

        // 3 laços aninhados => 3 x 3 x 3 = 27 combinações
        for (String pagamento : PAGAMENTOS) {
            for (String frete : FRETES) {
                for (String perfil : PERFIS) {
                    contador++;
                    String nome = String.format("Combinacao %02d: %s + %s + %s",
                            contador, pagamento, frete, perfil);

                    testes.add(dynamicTest(nome, () -> {
                        double total = servico.calcularTotal(100, pagamento, frete, perfil);
                        assertTrue(total > 0,   "Total deve ser positivo: " + total);
                        assertTrue(total <= 140, "Total não pode passar de 140: " + total);
                    }));
                }
            }
        }

        return testes; // 27 testes dinâmicos
    }
}
