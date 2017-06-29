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
        } else {
            println 'populando usuário admin - ok'
        }

        def sec = new Secretario(
                username: "sec",
                password: "sec",
                nome: "Secretário",
                telefone: "",
                enabled: true
        )

        sec.save()
        if (sec.hasErrors()) {
            println sec.errors
        } else {
            println 'populando usuário sec - ok'
        }

        def fisio = new Fisioterapeuta(
                username: "fisio",
                password: "fisio",
                nome: "Fisioterapeuta",
                crefitoEstado: "",
                crefitoID: "",
                universidade: "",
                dataNascimento: "",
                endereco: "",
                celular: "",
                enabled: true
        )

        fisio.save()
        if (fisio.hasErrors()) {
            println fisio.errors
        } else {
            println 'populando usuário fisio - ok'
        }
    }
    def destroy = {
    }
}
