package br.ufscar.dc.fisio

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(Secretario.AUTHORITY)
class FichaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured([Secretario.AUTHORITY, Fisioterapeuta.AUTHORITY])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Ficha.list(params), model:[fichaCount: Ficha.count()]
    }

    @Secured([Secretario.AUTHORITY, Fisioterapeuta.AUTHORITY])
    def show(Ficha ficha) {
        respond ficha
    }

    def create() {
        respond new Ficha(params)
    }

    @Transactional
    def save(Ficha ficha) {
        if (ficha == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ficha.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ficha.errors, view:'create'
            return
        }

        ficha.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ficha.label', default: 'Ficha'), ficha.id])
                redirect ficha
            }
            '*' { respond ficha, [status: CREATED] }
        }
    }

    def edit(Ficha ficha) {
        respond ficha
    }

    @Transactional
    def update(Ficha ficha) {
        if (ficha == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ficha.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ficha.errors, view:'edit'
            return
        }

        ficha.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ficha.label', default: 'Ficha'), ficha.id])
                redirect ficha
            }
            '*'{ respond ficha, [status: OK] }
        }
    }

    @Transactional
    def delete(Ficha ficha) {

        if (ficha == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        ficha.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ficha.label', default: 'Ficha'), ficha.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ficha.label', default: 'Ficha'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
