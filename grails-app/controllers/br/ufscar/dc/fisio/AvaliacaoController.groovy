package br.ufscar.dc.fisio

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(Fisioterapeuta.AUTHORITY)
class AvaliacaoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Avaliacao.list(params), model: [avaliacaoCount: Avaliacao.count()]
    }

    def searchResults() {
        // Source: https://stackoverflow.com/a/1723851
        def entryCriteria = Avaliacao.createCriteria()
        def results = entryCriteria.list {
            if (params?.lesao) {
                ficha {
                    ilike("lesao", "%${params.lesao}%")
                }
            }
            if (params?.paciente) {
                ficha {
                    paciente {
                        ilike("nome", "%${params.paciente}%")
                    }
                }
            }
        }
        respond results, model: [avalicaoCount: results.size()],
                view: 'index'
    }

    def show(Avaliacao avaliacao) {
        respond avaliacao
    }

    def create() {
        respond new Avaliacao(params)
    }

    @Transactional
    def save(Avaliacao avaliacao) {
        if (avaliacao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (avaliacao.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond avaliacao.errors, view: 'create'
            return
        }

        avaliacao.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), avaliacao.id])
                redirect avaliacao
            }
            '*' { respond avaliacao, [status: CREATED] }
        }
    }

    def edit(Avaliacao avaliacao) {
        respond avaliacao
    }

    @Transactional
    def update(Avaliacao avaliacao) {
        if (avaliacao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (avaliacao.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond avaliacao.errors, view: 'edit'
            return
        }

        avaliacao.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), avaliacao.id])
                redirect avaliacao
            }
            '*' { respond avaliacao, [status: OK] }
        }
    }

    @Transactional
    def delete(Avaliacao avaliacao) {

        if (avaliacao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        avaliacao.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), avaliacao.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'avaliacao.label', default: 'Avaliacao'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
