package br.ufscar.dc.fisio

class Ficha {

    static hasMany = [consultas: Consulta]
    static hasOne = [avaliacao: Avaliacao]

    static constraints = {
        avaliacao unique: true
        area(blank: false)
        lesao(blank: false)
        gravidade(blank: false)
        status(blank: false)
        medico(blank: false)
        dataInicio(blank: false)
    }

    String area
    String lesao
    String gravidade
    String status
    String medico
    String encaminhamento
    Date dataInicio
    Date dataTermino

    Paciente paciente
    Fisioterapeuta fisioterapeuta
}
