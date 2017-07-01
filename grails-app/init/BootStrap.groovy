import br.ufscar.dc.fisio.Administrador
import br.ufscar.dc.fisio.Fisioterapeuta
import br.ufscar.dc.fisio.Paciente
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
        println admin.errors

        def sec = new Secretario(
                username: "sec",
                password: "sec",
                nome: "Secretário",
                telefone: "",
                enabled: true
        )
        sec.save()
        println sec.errors

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
        println fisio.errors
//        def paciente = new Paciente(
//                nome: "nome",
//                ...
//        )
//        paciente.save()
//        println paciente.errors
    }
    def destroy = {
    }
}
