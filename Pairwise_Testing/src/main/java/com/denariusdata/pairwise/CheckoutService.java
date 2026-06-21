package com.denariusdata.pairwise;

/**
 * Sistema Sob Teste (SUT) da demonstração de Pairwise Testing.
 *
 * Calcula o total de um checkout a partir de:
 *   - subtotal  : valor base do carrinho
 *   - pagamento : "Cartao", "Pix" ou "Boleto"
 *   - frete     : "Normal", "Expresso" ou "Economico"
 *   - perfil    : "Novo", "Recorrente" ou "VIP"
 *
 * São 3 parâmetros com 3 valores cada => 27 combinações possíveis (3x3x3).
 * O Pairwise consegue cobrir TODOS os pares com apenas 9 casos.
 */
public class CheckoutService {

    /**
     * BUG PROPOSITAL (didático).
     *
     * Quando esta constante for "true", o método introduz um erro de cálculo
     * que SÓ aparece na combinação de DOIS parâmetros: pagamento "Pix" + frete "Expresso".
     *
     * Esse é justamente o tipo de falha de integração que o Pairwise Testing
     * foi feito para detectar: como todos os pares são cobertos pelos 9 casos,
     * o par (Pix, Expresso) é necessariamente testado e o bug é pego.
     *
     * Troque para "true" para ver o teste Pairwise falhar no caso 5.
     */
    static final boolean BUG_PROPOSITAL = false;

    /**
     * Calcula o total do checkout aplicando frete e descontos.
     *
     * @param subtotal  valor base (ex.: 100)
     * @param pagamento "Cartao", "Pix" ou "Boleto"
     * @param frete     "Normal", "Expresso" ou "Economico"
     * @param perfil    "Novo", "Recorrente" ou "VIP"
     * @return total calculado
     * @throws IllegalArgumentException se algum valor for inválido
     */
    public double calcularTotal(double subtotal, String pagamento, String frete, String perfil) {
        // Frete somado ao subtotal
        double valorFrete;
        switch (frete) {
            case "Normal":    valorFrete = 20; break;
            case "Expresso":  valorFrete = 40; break;
            case "Economico": valorFrete = 10; break;
            default: throw new IllegalArgumentException("Frete inválido: " + frete);
        }

        // Desconto por perfil (multiplicativo)
        double fatorPerfil;
        switch (perfil) {
            case "VIP":        fatorPerfil = 0.90; break;
            case "Recorrente": fatorPerfil = 0.95; break;
            case "Novo":       fatorPerfil = 1.00; break;
            default: throw new IllegalArgumentException("Perfil inválido: " + perfil);
        }

        // Desconto por pagamento (multiplicativo)
        double fatorPagamento;
        switch (pagamento) {
            case "Boleto": fatorPagamento = 0.98; break;
            case "Pix":    fatorPagamento = 0.97; break;
            case "Cartao": fatorPagamento = 1.00; break;
            default: throw new IllegalArgumentException("Pagamento inválido: " + pagamento);
        }

        double total = (subtotal + valorFrete) * fatorPerfil * fatorPagamento;

        // --- BUG PROPOSITAL: falha de integração entre DOIS parâmetros ---
        // Só dispara quando pagamento == "Pix" E frete == "Expresso".
        // É proposital para demonstrar que o Pairwise (9 casos) detecta o defeito.
        if (BUG_PROPOSITAL && pagamento.equals("Pix") && frete.equals("Expresso")) {
            total += 999;
        }
        // ----------------------------------------------------------------

        return total;
    }
}
