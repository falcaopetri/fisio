<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fisioterapeuta.label', default: 'Fisioterapeuta')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-fisioterapeuta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link controller="logout">Logout</g:link></li>
            </ul>
        </div>
        <div id="list-fisioterapeuta" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:form controller="fisioterapeuta" action="searchResults">
                Nome: <g:textField name="nome" value="${nome}"/>
                <g:submitButton name="submit" value="Pesquisar"/>
            </g:form>

            <f:table collection="${fisioterapeutaList}" />

            <div class="pagination">
                <g:paginate total="${fisioterapeutaCount ?: 0}" />
            </div>
        </div>
    </body>
</html>