from allpairspy import AllPairs
from main import calcular_elegibilidade_seguro


def obter_resultado_esperado(
        idade_grupo,
        possui_sinistro,
        tipo_veiculo):

    if idade_grupo == "Jovem (Abaixo 25)":
        if possui_sinistro == "Sim":
            return "Recusado"
        else:
            return "Aprovado Risco Alto"

    else:
        if tipo_veiculo == "Utilitario":
            return "Aprovado Risco Medio"
        else:
            return "Aprovado Risco Padrao"


def testar_sistema_pairwise():

    parametros = [

        ["Jovem (Abaixo 25)",
         "Adulto (25-60)",
         "Idoso (Mais 60)"],

        ["Sim", "Nao"],

        ["Passeio",
         "Utilitario",
         "Motocicleta"]
    ]

    print("=== CENÁRIOS GERADOS VIA PAIRWISE ===")

    for i, combinacao in enumerate(AllPairs(parametros), start=1):

        idade = combinacao[0]
        sinistro = combinacao[1]
        veiculo = combinacao[2]

        resultado_obtido = calcular_elegibilidade_seguro(
            idade,
            sinistro,
            veiculo
        )

        resultado_esperado = obter_resultado_esperado(
            idade,
            sinistro,
            veiculo
        )

        assert resultado_obtido == resultado_esperado

        print(
            f"Cenário {i}: "
            f"{combinacao} -> "
            f"{resultado_obtido}"
        )

    print("\nTodos os testes executados com sucesso!")


if __name__ == "__main__":
    testar_sistema_pairwise()
