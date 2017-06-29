package br.ufscar.dc.fisio

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RelatorioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Relatorio.list(params), model:[relatorioCount: Relatorio.count()]
    }

    def show(Relatorio relatorio) {
        respond relatorio
    }

    def create() {
        respond new Relatorio(params)
    }

    @Transactional
    def save(Relatorio relatorio) {
        if (relatorio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (relatorio.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond relatorio.errors, view:'create'
            return
        }

        relatorio.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'relatorio.label', default: 'Relatorio'), relatorio.id])
                redirect relatorio
            }
            '*' { respond relatorio, [status: CREATED] }
        }
    }

    def edit(Relatorio relatorio) {
        respond relatorio
    }

    @Transactional
    def update(Relatorio relatorio) {
        if (relatorio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (relatorio.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond relatorio.errors, view:'edit'
            return
        }

        relatorio.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'relatorio.label', default: 'Relatorio'), relatorio.id])
                redirect relatorio
            }
            '*'{ respond relatorio, [status: OK] }
        }
    }

    @Transactional
    def delete(Relatorio relatorio) {

        if (relatorio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        relatorio.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'relatorio.label', default: 'Relatorio'), relatorio.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'relatorio.label', default: 'Relatorio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
