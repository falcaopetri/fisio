package br.ufscar.dc.fisio

class Avaliacao {

    static constraints = {
        hma(blank: false)
        inspecaoGeral(blank: false)
        avaMovAtivo(blank: false)
        avaFuncao(blank: false)
        palpacao(blank: false)
        funcaoMuscular(blank: false)
        provaDeCumprimentoMusc(blank: false)
        goniometria(blank: false)
        perimetria(blank: false)
        mensuracao(blank: false)
        diagnosticoFisio(blank: false)
        objetivosTratamento(blank: false)
        tratamento(blank: false)
        comportamentoDor(blank: false)
    }

    Ficha ficha

    Date dataTrauma
    String localTrauma
    String tipoTratamento
    String materialDeSintese
    Date dataCirurgia
    String atividadesPermitidas
    String hma
    String inspecaoGeral
    String avaMovAtivo
    String avaFuncao
    String palpacao
    String funcaoMuscular
    String provaDeCumprimentoMusc
    String sensibilidade
    String reflexos
    String goniometria
    String testesEspeciais
    String perimetria
    String mensuracao
    String examesComplementares
    String diagnosticoFisio
    String objetivosTratamento
    String tratamento
    String comportamentoDor
}