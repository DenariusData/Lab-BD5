package com.denariusdata.pairwise;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TESTE PAIRWISE — o destaque da apresentação.
 *
 * Executa APENAS 9 casos. Esses 9 casos foram escolhidos de forma que
 * TODOS os pares possíveis de valores (pagamento x frete, pagamento x perfil,
 * frete x perfil) apareçam ao menos uma vez. Por isso, qualquer bug que dependa
 * da combinação de DOIS parâmetros é necessariamente exercitado.
 *
 * 9 casos no lugar de 27 (-66%), sem perder a capacidade de pegar bugs de integração.
 *
 * Quando CheckoutService.BUG_PROPOSITAL for "true", o Caso 5 (Pix + Expresso)
 * deve FALHAR — provando que esses 9 testes detectam a falha.
 */
class CheckoutPairwiseTest {

    private final CheckoutService servico = new CheckoutService();

    /**
     * Os 9 casos do Pairwise (gerados, por exemplo, pelo Microsoft PICT).
     * Cada Arguments traz: pagamento, frete, perfil.
     */
    static Stream<Arguments> casosPairwise() {
        return Stream.of(
            Arguments.of("Cartao", "Normal",    "Novo"),        // Caso 1
            Arguments.of("Cartao", "Expresso",  "Recorrente"),  // Caso 2
            Arguments.of("Cartao", "Economico", "VIP"),         // Caso 3
            Arguments.of("Pix",    "Normal",    "Recorrente"),  // Caso 4
            Arguments.of("Pix",    "Expresso",  "VIP"),         // Caso 5  <- pega o bug
            Arguments.of("Pix",    "Economico", "Novo"),        // Caso 6
            Arguments.of("Boleto", "Normal",    "VIP"),         // Caso 7
            Arguments.of("Boleto", "Expresso",  "Novo"),        // Caso 8
            Arguments.of("Boleto", "Economico", "Recorrente")   // Caso 9
        );
    }

    @ParameterizedTest(name = "Caso {index}: {0} + {1} + {2}")
    @MethodSource("casosPairwise")
    void deveRespeitarInvariantesDeNegocio(String pagamento, String frete, String perfil) {
        double total = servico.calcularTotal(100, pagamento, frete, perfil);

        // Invariantes de negócio:
        // total sempre positivo
        assertTrue(total > 0,
                "O total deve ser positivo, mas foi " + total
                        + " para " + pagamento + " + " + frete + " + " + perfil);

        // total nunca maior que subtotal + frete máximo (100 + 40 = 140),
        // pois os descontos só reduzem o valor. O bug (+999) quebra este invariante.
        assertTrue(total <= 140,
                "O total não pode passar de 140 (100 + frete máximo), mas foi " + total
                        + " para " + pagamento + " + " + frete + " + " + perfil
                        + " — possível bug de integração entre parâmetros!");
    }
}
