package br.ufscar.dc.fisio

import src.Util


class Consulta {
    static hasOne = [relatorio: Relatorio, fisioterapeuta: Fisioterapeuta]

    static constraints = {
        relatorio(unique: true, nullable: true, blank: true, display: false)
        data(blank: false)
    }

    Date data
    Ficha ficha

    @Override
    String toString() {
        return ficha?.paciente.toString() + " - " + Util.df.format(data)
    }
}
