package br.ufscar.dc.fisio

class Fisioterapeuta extends Usuario {
    // Read: https://stackoverflow.com/a/26712097
    static final String AUTHORITY = "ROLE_FISIOTERAPEUTA"

    static hasMany = [fichas: Ficha, consultas:Consulta]

    static constraints = {
        nome(blank: false)
        crefitoEstado(blank: false)
        crefitoID(blank: false)
        universidade(blank: false)
        dataNascimento(blank: false)
        endereco(blank: false)
        celular(blank: false)
    }

    String nome
    String crefitoEstado
    String crefitoID
    String universidade
    Date dataNascimento
    String endereco
    String telefone
    String celular

    String toString() {
        return nome
    }

    @Override
    Fisioterapeuta save() {
        Fisioterapeuta user = super.save()
        def papel = Papel.findByAuthority(Fisioterapeuta.AUTHORITY) ?:
                new Papel(authority: Fisioterapeuta.AUTHORITY).save()

        UsuarioPapel.create(user, papel)
        return user
    }
}
