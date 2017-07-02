import br.ufscar.dc.fisio.Administrador
import br.ufscar.dc.fisio.Avaliacao
import br.ufscar.dc.fisio.Consulta
import br.ufscar.dc.fisio.Ficha
import br.ufscar.dc.fisio.Fisioterapeuta
import br.ufscar.dc.fisio.Paciente
import br.ufscar.dc.fisio.Relatorio
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

        def admin2 = new Administrador(
                username: "admin2",
                password: "admin2",
                nome: "Antônio Carlos",
                enabled: true
        )
        admin2.save()
        println admin2.errors



        def sec2 = new Secretario(
                username: "sec2",
                password: "sec2",
                nome: "José Antônio",
                telefone: "(16) 33333-3333",
                enabled: true
        )
        sec2.save()
        println sec2.errors

        def sec3 = new Secretario(
                username: "sec3",
                password: "sec3",
                nome: "João Paulo",
                telefone: "(14) 32222-2222",
                enabled: true
        )
        sec3.save()
        println sec3.errors

        def fisio2 = new Fisioterapeuta(
                username: "fisio2",
                password: "fisio2",
                nome: "Muriel Mauch",
                crefitoEstado: "11",
                crefitoID: "35261",
                universidade: "Universidade Federal de São Carlos",
                dataNascimento: new GregorianCalendar(1996, 2, 28).getTime(),
                endereco: "Avenida São Carlos, 1111",
                celular: "(16) 99999-1111",
                enabled: true
        )
        fisio2.save()
        println fisio2.errors

        def fisio3 = new Fisioterapeuta(
                username: "fisio3",
                password: "fisio3",
                nome: "José ViCtor",
                crefitoEstado: "12",
                crefitoID: "21999",
                universidade: "Universidade Federal do Paraná",
                dataNascimento: new GregorianCalendar(1996, 2, 27).getTime(),
                endereco: "Avenida Trabalhado São Carlense, 444",
                celular: "(16) 99999-2222",
                enabled: true
        )
        fisio3.save()
        println fisio3.errors

        def pac = new Paciente(
                nome: "Júnior Santos",
                dataNascimento: new GregorianCalendar(1996, 1, 11).getTime(),
                idade: 21,
                profissao: "Estudante",
                estadoCivil: "Casado",
                endereco: "Rua André Veloni, 456",
                cidade: "Ribeirão Preto",
                telefone: "(16) 33333-1111",
                celular: "(16) 99999-3333",
                nomeEmergencia: "Maria",
                telefoneEmergencia: "(16) 33333-4444"
        )
        pac.save()
        println pac.errors

        def pac2 = new Paciente(
                nome: "Júlia Santos",
                dataNascimento: new GregorianCalendar(1997, 9, 8).getTime(),
                idade: 19,
                profissao: "Engenheira",
                estadoCivil: "Solteiro",
                endereco: "Rua Angelo Bruno, 654",
                cidade: "Ribeirão Preto",
                telefone: "(16) 33333-5555",
                celular: "(16) 99999-4444",
                nomeEmergencia: "Paulo",
                telefoneEmergencia: "(16) 33333-6666"
        )
        pac2.save()
        println pac2.errors

        def pac3 = new Paciente(
                nome: "Geovanna Molina",
                dataNascimento: new GregorianCalendar(1996, 9, 22).getTime(),
                idade: 26,
                profissao: "Advogada",
                estadoCivil: "Casado",
                endereco: "Avenida Presidente Vargas, 2554",
                cidade: "São Paulo",
                telefone: "(11) 33333-7777",
                celular: "(11) 99999-7777",
                nomeEmergencia: "Júnior",
                telefoneEmergencia: "(16) 33333-8888"
        )
        pac3.save()
        println pac3.errors

        def ficha = new Ficha(
                area: "Ortopedia",
                lesao: "Lombar",
                gravidade: "Mediana",
                status: "Em tratamento",
                medico: "João Kleber",
                encaminhamento: "O senhor Júnior tem uma lesão na lombar que deve ser tratada antes de seu agravamento",
                dataInicio: new GregorianCalendar(2017, 6, 30).getTime(),
                dataTermino: "",
                paciente: pac,
                fisioterapeuta: fisio
        )
        ficha.save()
        println ficha.errors


        def ficha2 = new Ficha(
                area: "Joelho",
                lesao: "Rompimento do Ligamento Crusado",
                gravidade: "Gravissima",
                status: "Tratamento concluído",
                medico: "Paulo César",
                encaminhamento: "Urgente!",
                dataInicio: new GregorianCalendar(2013, 5, 10).getTime(),
                dataTermino: new GregorianCalendar(2014, 5, 22).getTime(),
                paciente: pac,
                fisioterapeuta: fisio2
        )
        ficha2.save()
        println ficha2.errors

        def ficha3 = new Ficha(
                area: "Saúde da Mulher",
                lesao: "Fortalecimento para Gravidez",
                gravidade: "Leve",
                status: "Tratamento não iniciado",
                medico: "Fabiana Junqueira",
                encaminhamento: "Exercícios para fortalecimento da parede do útero são recomendados para uma gravidez saúdavel",
                dataInicio: new GregorianCalendar(2017, 7, 2).getTime(),
                dataTermino: "",
                paciente: pac3,
                fisioterapeuta: fisio3
        )
        ficha3.save()
        println ficha3.errors

        def con = new Consulta(
                fisioterapeuta: fisio,
                data: new GregorianCalendar(2017, 7, 1).getTime(),
                ficha: ficha
        )
        con.save()
        println con.errors

        def con2 = new Consulta(
                fisioterapeuta: fisio2,
                data: new GregorianCalendar(2013, 7, 10).getTime(),
                ficha: ficha2
        )
        con2.save()
        println con2.errors

        def con3 = new Consulta(
                fisioterapeuta: fisio3,
                data: new GregorianCalendar(2013, 11, 10).getTime(),
                ficha: ficha2
        )
        con3.save()
        println con3.errors

        def con4 = new Consulta(
                fisioterapeuta: fisio,
                data: new GregorianCalendar(2014, 5, 10).getTime(),
                ficha: ficha2
        )
        con4.save()
        println con4.errors

        def rel = new Relatorio(
                consulta: con4,
                exercicios: "Extensora - 2,5kg; Agachamento - Sem carga; Stiff - Sem carga",
                status: "Evolução",
                acontecimentos: "A consulta ocorreu bem. Houve dificuldades do paciente em realizar o exercício Sniff",
                observacoes: ""
        )
        rel.save()
        println rel.errors

        def rel2 = new Relatorio(
                consulta: con2,
                exercicios: "Extensora - 5kg; Agachamento - Sem carga; Stiff - Sem carga",
                status: "Involução",
                acontecimentos: "A consulta e o tratamento ocorreram de maneira adequada",
                observacoes: "O paciente teve uma involução em função de uma torção em casa"
        )
        rel2.save()
        println rel2.errors

        def rel3 = new Relatorio(
                consulta: con3,
                exercicios: "Extensora - 10kg; Agachamento - Sem carga; Elevação das pernas - 5kg",
                status: "Evolução",
                acontecimentos: "A consulta eo tratamento ocorreram de maneira adequada, o paciênte se mostrou bem recuperado e pode ter alta",
                observacoes: "Alta do paciente"
        )
        rel3.save()
        println rel3.errors

        def ava = new Avaliacao(
                ficha: ficha,
                dataTrauma: "",
                localTrauma: "",
                tipoTratamento: "",
                materialDeSintese: "",
                dataCirurgia: "",
                atividadesPermitidas: "",
                hma: "8",
                inspecaoGeral: "Normal",
                avaMovAtivo: "Presença de movimento aberrante",
                avaFuncao: "Dificuldade de permanecer parado por tempo prolongado",
                palpacao: "Dor a mobilização de L1 e l2",
                funcaoMuscular: "Fraqueza muscular de extensores de tronco e para vertebrais lombares",
                provaDeCumprimentoMusc: "Encurtamento muscular de peitoral maior e flexões de tronco",
                sensibilidade: "Não avaliar",
                reflexos: "Não avaliar",
                goniometria: "40° de flexão de lombar, 15° de extensão de lombar e 20°de inclinação de lombar e 30°de rotação",
                testesEspeciais: "",
                perimetria: "Não tem perimetria de lombar",
                mensuracao: "Não tem mensuração de lombar",
                examesComplementares: "Spring test e de instabilidade em prono",
                diagnosticoFisio: "Deficit de coordenação  e instabilidade lombar, adm reduzida para flexores, extensores e inclinadores de tronco, encurtamento muscular de flexores de tronco e peitoral maio, hipomobilidade vertebral e fraqueza muscular de extensores de tronco",
                objetivosTratamento: "Ganho de adm, de mobilidade vertebral e força muscular para músculos globais de tronco",
                tratamento: "Exercício de endurance, alongamento e mobilização articular",
                comportamentoDor: "Dor local que refere para membro inferior direito"
        )
        ava.save()
        println ava.errors

        def ava2 = new Avaliacao(
                ficha: ficha2,
                dataTrauma: new GregorianCalendar(2013, 5, 1).getTime(),
                localTrauma: "Ligamentos do Joelho Direito",
                tipoTratamento: "Cirúrgico",
                materialDeSintese: "",
                dataCirurgia: new GregorianCalendar(2013, 5, 5).getTime(),
                atividadesPermitidas: "Nada",
                hma: "10",
                inspecaoGeral: "Tensa",
                avaMovAtivo: "Ausencia de movimento significante",
                avaFuncao: "Dificuldade de movimento muscular",
                palpacao: "Dor no joelho",
                funcaoMuscular: "Fraqueza muscular",
                provaDeCumprimentoMusc: "Encurtamento muscular em função do processo cirurgico",
                sensibilidade: "Zero",
                reflexos: "Zero",
                goniometria: "5º de flexão",
                testesEspeciais: "",
                perimetria: "Não",
                mensuracao: "Não",
                examesComplementares: "",
                diagnosticoFisio: "Ele está em maus lençois",
                objetivosTratamento: "Tratar",
                tratamento: "Algum",
                comportamentoDor: "Aguda"
        )
        ava2.save()
        println ava2.errors

        def ava3 = new Avaliacao(
                ficha: ficha3,
                dataTrauma: "",
                localTrauma: "",
                tipoTratamento: "",
                materialDeSintese: "",
                dataCirurgia: "",
                atividadesPermitidas: "",
                hma: "3",
                inspecaoGeral: "Parede do Utero muito fraca",
                avaMovAtivo: "Total",
                avaFuncao: "Total",
                palpacao: "Não há",
                funcaoMuscular: "Total",
                provaDeCumprimentoMusc: "Pouca extensão",
                sensibilidade: "Total",
                reflexos: "Total",
                goniometria: "Não há",
                testesEspeciais: "",
                perimetria: "Não há",
                mensuracao: "Não há",
                examesComplementares: "",
                diagnosticoFisio: "Necessidade de fortalecer a parede do útero",
                objetivosTratamento: "Fortalecer a parede do útero",
                tratamento: "Exercícios",
                comportamentoDor: "Não há dor associada"
        )
        ava3.save()
        println ava3.errors
    }
    def destroy = {
    }
}
