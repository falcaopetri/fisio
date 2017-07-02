package br.ufscar.dc.fisio

class Relatorio {
    enum Status {
        EVOLUCAO("Evolução"), ESTAGNACAO("Estagnação"), INVOLUCAO("Involução")
        private final String id

        private Status(String id) {
            this.id = id
        }

        int value() { id }
    }

    static constraints = {
        exercicios(blank: false)
        status(inList: Status.values()*.id, blank: false)
        acontecimentos(blank: false)
        observacoes(nullable: true)
    }

    Consulta consulta
    String exercicios
    String status
    String acontecimentos
    String observacoes

    @Override
    String toString() {
        return consulta.toString() + " - " + status
    }
}
