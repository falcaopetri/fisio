package br.ufscar.dc.fisio

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UsuarioPapelController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UsuarioPapel.list(params), model:[usuarioPapelCount: UsuarioPapel.count()]
    }

    def show(UsuarioPapel usuarioPapel) {
        respond usuarioPapel
    }

    def create() {
        respond new UsuarioPapel(params)
    }

    @Transactional
    def save(UsuarioPapel usuarioPapel) {
        if (usuarioPapel == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuarioPapel.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuarioPapel.errors, view:'create'
            return
        }

        usuarioPapel.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), usuarioPapel.id])
                redirect usuarioPapel
            }
            '*' { respond usuarioPapel, [status: CREATED] }
        }
    }

    def edit(UsuarioPapel usuarioPapel) {
        respond usuarioPapel
    }

    @Transactional
    def update(UsuarioPapel usuarioPapel) {
        if (usuarioPapel == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuarioPapel.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuarioPapel.errors, view:'edit'
            return
        }

        usuarioPapel.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), usuarioPapel.id])
                redirect usuarioPapel
            }
            '*'{ respond usuarioPapel, [status: OK] }
        }
    }

    @Transactional
    def delete(UsuarioPapel usuarioPapel) {

        if (usuarioPapel == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        usuarioPapel.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), usuarioPapel.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioPapel.label', default: 'UsuarioPapel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
