import br.ufscar.dc.fisio.Administrador

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

    }
    def destroy = {
    }
}
