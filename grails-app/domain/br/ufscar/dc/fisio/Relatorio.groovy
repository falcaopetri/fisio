package br.ufscar.dc.fisio

class Relatorio {

    static constraints = {
        exercicios(blank: false)
        status(blank: false)
        acontecimentos(blank: false)
    }

    Consulta consulta
    String exercicios
    String status
    String acontecimentos
    String observacoes
}
