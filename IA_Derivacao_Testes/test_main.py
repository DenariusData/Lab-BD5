from main import calcular_elegibilidade_seguro


def test_jovem_com_sinistro():
    assert calcular_elegibilidade_seguro(
        "Jovem (Abaixo 25)",
        "Sim",
        "Passeio"
    ) == "Recusado"


def test_jovem_sem_sinistro():
    assert calcular_elegibilidade_seguro(
        "Jovem (Abaixo 25)",
        "Nao",
        "Passeio"
    ) == "Aprovado Risco Alto"


def test_adulto_utilitario():
    assert calcular_elegibilidade_seguro(
        "Adulto (25-60)",
        "Sim",
        "Utilitario"
    ) == "Aprovado Risco Medio"


def test_adulto_veiculo_comum():
    assert calcular_elegibilidade_seguro(
        "Adulto (25-60)",
        "Nao",
        "Passeio"
    ) == "Aprovado Risco Padrao"
