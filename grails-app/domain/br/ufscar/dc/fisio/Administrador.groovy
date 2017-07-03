package br.ufscar.dc.fisio

import src.Util

class Administrador extends Usuario {
    // Read: https://stackoverflow.com/a/26712097
    static final String AUTHORITY = "ROLE_ADMIN"

    static constraints = {
        nome(blank: false)
    }

    String nome

    @Override
    Administrador save() {
        Usuario user = super.save()
        Util.savePapel(user)
        return user
    }

    @Override
    Administrador save(boolean b) {
        Usuario user = super.save(b)
        Util.savePapel(user)
        return user
    }

    @Override
    Administrador save(Map map) {
        Usuario user = super.save(map)
        Util.savePapel(user)
        return user
    }

    @Override
    String getAuthorityName() {
        return this.class.AUTHORITY
    }
}
