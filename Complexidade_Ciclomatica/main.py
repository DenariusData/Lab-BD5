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


resultado = calcular_elegibilidade_seguro(
    "Jovem",
    "Sim",
    "Passeio"
)

print(resultado)