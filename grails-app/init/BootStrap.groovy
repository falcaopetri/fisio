import br.ufscar.dc.fisio.Administrador
import br.ufscar.dc.fisio.Fisioterapeuta
import br.ufscar.dc.fisio.Secretario

class BootStrap {

    def init = { servletContext ->

        def admin = new Administrador(
                username: "admin",
                password: "admin",
                nome: "Administrador",
                enabled: true
        )

        admin.save()
        if (admin.hasErrors()) {
            println admin.errors
        }

        println 'populando usuário admin - ok'

        def sec = new Secretario(
                username: "sec",
                password: "sec",
                nome: "Secretário",
                enabled: true
        )

        sec.save()
        if (sec.hasErrors()) {
            println sec.errors
        }

        println 'populando usuário sec - ok'

        def fisio = new Fisioterapeuta(
                username: "fisio",
                password: "fisio",
                nome: "Fisioterapeuta",
                enabled: true
        )

        fisio.save()
        if (fisio.hasErrors()) {
            println fisio.errors
        }

        println 'populando usuário fisio - ok'
    }
    def destroy = {
    }
}
