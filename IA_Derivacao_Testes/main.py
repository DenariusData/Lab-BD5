def calcular_elegibilidade_seguro(idade_grupo, possui_sinistro, tipo_veiculo):

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
