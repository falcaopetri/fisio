package br.ufscar.dc.fisio

class Ficha {
    enum Status {
        NAO_INICIADO("Tratamento não iniciado"), TRATAMENTO("Em tratamento")
        , CONCLUIDO("Tratamento concluído")
        private final String id

        private Status(String id) {
            this.id = id
        }

        int value() { id }
    }

    enum Gravidade {
        LEVE("Leve"), MEDIANA("Mediana"), GRAVE("Grave"), GRAVISSIMA("Gravissima")
        private final String id

        private Gravidade(String id) {
            this.id = id
        }

        int value() { id }
    }


    static hasMany = [consultas: Consulta]
    static hasOne = [avaliacao: Avaliacao]

    static constraints = {
        avaliacao(unique: true, nullable: true, blank: true)
        consultas(blank: true)
        area(blank: false)
        lesao(blank: false)
        gravidade(inList: Gravidade.values()*.id, blank: false)
        status(inList: Status.values()*.id, blank: false)
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

    @Override
    String toString() {
        return paciente.toString() + " - " + lesao
    }
}
