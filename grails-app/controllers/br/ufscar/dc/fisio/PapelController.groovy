package br.ufscar.dc.fisio

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PapelController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Papel.list(params), model:[papelCount: Papel.count()]
    }

    def show(Papel papel) {
        respond papel
    }

    def create() {
        respond new Papel(params)
    }

    @Transactional
    def save(Papel papel) {
        if (papel == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (papel.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond papel.errors, view:'create'
            return
        }

        papel.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'papel.label', default: 'Papel'), papel.id])
                redirect papel
            }
            '*' { respond papel, [status: CREATED] }
        }
    }

    def edit(Papel papel) {
        respond papel
    }

    @Transactional
    def update(Papel papel) {
        if (papel == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (papel.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond papel.errors, view:'edit'
            return
        }

        papel.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'papel.label', default: 'Papel'), papel.id])
                redirect papel
            }
            '*'{ respond papel, [status: OK] }
        }
    }

    @Transactional
    def delete(Papel papel) {

        if (papel == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        papel.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'papel.label', default: 'Papel'), papel.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'papel.label', default: 'Papel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
