package br.ufscar.dc.fisio

class Administrador extends Usuario {
    // Read: https://stackoverflow.com/a/26712097
    static final String AUTHORITY = "ROLE_ADMIN"

    static constraints = {
        nome(blank: false)
    }

    String nome

    @Override
    Administrador save() {
        Administrador user = super.save()
        def papel = Papel.findByAuthority(Administrador.AUTHORITY) ?:
                new Papel(authority: Administrador.AUTHORITY).save()

        UsuarioPapel.create(user, papel)
        return user
    }
}
