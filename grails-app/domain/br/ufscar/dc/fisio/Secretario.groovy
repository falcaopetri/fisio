package br.ufscar.dc.fisio

import org.grails.datastore.gorm.GormEntity
import src.Util

class Secretario extends Usuario {
    // Read: https://stackoverflow.com/a/26712097
    static final String AUTHORITY = "ROLE_SECRETARIO"

    static constraints = {
        nome(blank: false)
        telefone(nullable: true)
    }

    String nome
    String telefone


    @Override
    Secretario save() {
        Usuario user = super.save()
        Util.savePapel(user)
        return user
    }

    @Override
    Secretario save(boolean b) {
        Usuario user = super.save(b)
        Util.savePapel(user)
        return user
    }

    @Override
    Secretario save(Map map) {
        Usuario user = super.save(map)
        Util.savePapel(user)
        return user
    }

    @Override
    String getAuthorityName() {
        return this.class.AUTHORITY
    }
}
