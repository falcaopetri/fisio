package br.ufscar.dc.fisio

class Consulta {
    static hasOne = [relatorio: Relatorio]

    static constraints = {
        relatorio unique: true
        data(blank: false)
    }

    Date data
}
