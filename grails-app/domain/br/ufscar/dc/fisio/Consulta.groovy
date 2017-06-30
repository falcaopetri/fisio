package br.ufscar.dc.fisio

class Consulta {
    static hasOne = [relatorio: Relatorio, fisioterapeuta: Fisioterapeuta]

    static constraints = {
        relatorio(unique: true, nullable: true, blank: true)
        data(blank: false)
    }

    Date data
    Ficha ficha

    @Override
    String toString() {
        return ficha?.paciente.toString() + " - " + data
    }
}
