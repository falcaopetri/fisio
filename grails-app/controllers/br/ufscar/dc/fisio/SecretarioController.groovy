package br.ufscar.dc.fisio

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(Administrador.AUTHORITY)
class SecretarioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Secretario.list(params), model:[secretarioCount: Secretario.count()]
    }

    def show(Secretario secretario) {
        respond secretario
    }

    def create() {
        respond new Secretario(params)
    }

    @Transactional
    def save(Secretario secretario) {
        if (secretario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (secretario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond secretario.errors, view:'create'
            return
        }

        secretario.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'secretario.label', default: 'Secretario'), secretario.id])
                redirect secretario
            }
            '*' { respond secretario, [status: CREATED] }
        }
    }

    def edit(Secretario secretario) {
        respond secretario
    }

    @Transactional
    def update(Secretario secretario) {
        if (secretario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (secretario.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond secretario.errors, view:'edit'
            return
        }

        secretario.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'secretario.label', default: 'Secretario'), secretario.id])
                redirect secretario
            }
            '*'{ respond secretario, [status: OK] }
        }
    }

    @Transactional
    def delete(Secretario secretario) {

        if (secretario == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        secretario.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'secretario.label', default: 'Secretario'), secretario.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'secretario.label', default: 'Secretario'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
