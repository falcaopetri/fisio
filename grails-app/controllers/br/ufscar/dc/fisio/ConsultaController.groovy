package br.ufscar.dc.fisio

import grails.plugin.springsecurity.annotation.Secured
import org.grails.datastore.mapping.query.Restrictions

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(Secretario.AUTHORITY)
class ConsultaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured([Secretario.AUTHORITY, Fisioterapeuta.AUTHORITY])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Consulta.list(params), model: [consultaCount: Consulta.count()]
    }

    @Secured([Secretario.AUTHORITY, Fisioterapeuta.AUTHORITY])
    def searchResults() {
        // Source: https://stackoverflow.com/a/1723851
        def entryCriteria = Consulta.createCriteria()
        def results = entryCriteria.list {
            if (params?.paciente) {
                ficha {
                    paciente {
                        ilike("nome", "%${params.paciente}%")
                    }
                }
            }
        }
        respond results, model: [consultaCount: results.size()],
                view: 'index'
    }

    @Secured([Secretario.AUTHORITY, Fisioterapeuta.AUTHORITY])
    def show(Consulta consulta) {
        respond consulta
    }

    def create() {
        respond new Consulta(params)
    }

    @Transactional
    def save(Consulta consulta) {
        if (consulta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (consulta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond consulta.errors, view: 'create'
            return
        }

        consulta.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'consulta.label', default: 'Consulta'), consulta.id])
                redirect consulta
            }
            '*' { respond consulta, [status: CREATED] }
        }
    }

    def edit(Consulta consulta) {
        respond consulta
    }

    @Transactional
    def update(Consulta consulta) {
        if (consulta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (consulta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond consulta.errors, view: 'edit'
            return
        }

        consulta.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'consulta.label', default: 'Consulta'), consulta.id])
                redirect consulta
            }
            '*' { respond consulta, [status: OK] }
        }
    }

    @Transactional
    def delete(Consulta consulta) {

        if (consulta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        consulta.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'consulta.label', default: 'Consulta'), consulta.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'consulta.label', default: 'Consulta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
