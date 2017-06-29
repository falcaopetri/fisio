package br.ufscar.dc.fisio

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(Secretario.AUTHORITY)
class FisioterapeutaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Fisioterapeuta.list(params), model:[fisioterapeutaCount: Fisioterapeuta.count()]
    }

    def show(Fisioterapeuta fisioterapeuta) {
        respond fisioterapeuta
    }

    def create() {
        respond new Fisioterapeuta(params)
    }

    @Transactional
    def save(Fisioterapeuta fisioterapeuta) {
        if (fisioterapeuta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (fisioterapeuta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond fisioterapeuta.errors, view:'create'
            return
        }

        fisioterapeuta.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fisioterapeuta.label', default: 'Fisioterapeuta'), fisioterapeuta.id])
                redirect fisioterapeuta
            }
            '*' { respond fisioterapeuta, [status: CREATED] }
        }
    }

    def edit(Fisioterapeuta fisioterapeuta) {
        respond fisioterapeuta
    }

    @Transactional
    def update(Fisioterapeuta fisioterapeuta) {
        if (fisioterapeuta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (fisioterapeuta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond fisioterapeuta.errors, view:'edit'
            return
        }

        fisioterapeuta.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fisioterapeuta.label', default: 'Fisioterapeuta'), fisioterapeuta.id])
                redirect fisioterapeuta
            }
            '*'{ respond fisioterapeuta, [status: OK] }
        }
    }

    @Transactional
    def delete(Fisioterapeuta fisioterapeuta) {

        if (fisioterapeuta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        fisioterapeuta.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fisioterapeuta.label', default: 'Fisioterapeuta'), fisioterapeuta.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fisioterapeuta.label', default: 'Fisioterapeuta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
