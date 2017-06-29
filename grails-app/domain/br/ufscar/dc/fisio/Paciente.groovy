package br.ufscar.dc.fisio

class Paciente {

    static hasMany = [fichas: Ficha]
    static constraints = {
        nome(blank: false)
        dataNascimento(blank: false)
        profissao(blank: false)
        endereco(blank: false)
        cidade(blank: false)
        celular(blank: false)
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

    String toString() {
        return nome
    }
}