package br.ufscar.dc.fisio

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
        Secretario user = super.save()
        def papel = Papel.findByAuthority(Secretario.AUTHORITY) ?:
                new Papel(authority: Secretario.AUTHORITY).save()

        UsuarioPapel.create(user, papel)
        return user
    }

}
