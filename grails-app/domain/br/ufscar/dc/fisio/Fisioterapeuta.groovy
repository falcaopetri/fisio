package br.ufscar.dc.fisio

import src.Util

class Fisioterapeuta extends Usuario {
    // Read: https://stackoverflow.com/a/26712097
    static final String AUTHORITY = "ROLE_FISIOTERAPEUTA"

    static hasMany = [fichas: Ficha, consultas:Consulta]

    static constraints = {
        nome(blank: false)
        telefone(nullable: true)
        crefitoEstado(blank: false, nullable: true)
        crefitoID(blank: false, nullable: true)
        universidade(blank: false, nullable: true)
        dataNascimento(blank: false, nullable: true)
        endereco(blank: false, nullable: true)
        celular(blank: false, nullable: true)
    }

    String nome
    String crefitoEstado
    String crefitoID
    String universidade
    Date dataNascimento
    String endereco
    String telefone
    String celular

    @Override
    Fisioterapeuta save() {
        Usuario user = super.save()
        Util.savePapel(user)
        return user
    }

    @Override
    Fisioterapeuta save(boolean b) {
        Usuario user = super.save(b)
        Util.savePapel(user)
        return user
    }

    @Override
    Fisioterapeuta save(Map map) {
        Usuario user = super.save(map)
        Util.savePapel(user)
        return user
    }

    @Override
    String toString() {
        return nome
    }

    @Override
    String getAuthorityName() {
        return this.class.AUTHORITY
    }
}
