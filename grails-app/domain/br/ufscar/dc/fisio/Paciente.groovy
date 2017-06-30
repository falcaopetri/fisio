package br.ufscar.dc.fisio

class Paciente {
    enum EstadoCivil {
        SOLTEIRO("Solteiro"), CASADO("Casado")
        private final String id

        private EstadoCivil(String id) {
            this.id = id
        }

        int value() { id }
    }

    static hasMany = [fichas: Ficha]

    static constraints = {
        nome(blank: false)
        dataNascimento(blank: false)
        profissao(blank: false)
        endereco(blank: false)
        cidade(blank: false)
        celular(blank: false)
        estadoCivil(inList: EstadoCivil.values()*.id, blank: false)
        fichas(display: false)
    }

    String nome
    Date dataNascimento
    int idade
    String profissao
    String estadoCivil
    String endereco
    String cidade
    String telefone
    String celular
    String nomeEmergencia
    String telefoneEmergencia

    @Override
    String toString() {
        return nome
    }
}