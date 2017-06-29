package br.ufscar.dc.fisio

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(Administrador.AUTHORITY)
class AdministradorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Administrador.list(params), model:[administradorCount: Administrador.count()]
    }

    def show(Administrador administrador) {
        respond administrador
    }

    def create() {
        respond new Administrador(params)
    }

    @Transactional
    def save(Administrador administrador) {
        if (administrador == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (administrador.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond administrador.errors, view:'create'
            return
        }

        administrador.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'administrador.label', default: 'Administrador'), administrador.id])
                redirect administrador
            }
            '*' { respond administrador, [status: CREATED] }
        }
    }

    def edit(Administrador administrador) {
        respond administrador
    }

    @Transactional
    def update(Administrador administrador) {
        if (administrador == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (administrador.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond administrador.errors, view:'edit'
            return
        }

        administrador.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'administrador.label', default: 'Administrador'), administrador.id])
                redirect administrador
            }
            '*'{ respond administrador, [status: OK] }
        }
    }

    @Transactional
    def delete(Administrador administrador) {

        if (administrador == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        administrador.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'administrador.label', default: 'Administrador'), administrador.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'administrador.label', default: 'Administrador'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
