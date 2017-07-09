<%@ page import="grails.plugin.springsecurity.annotation.Secured; br.ufscar.dc.fisio.SecretarioController; br.ufscar.dc.fisio.AdministradorController" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>
<content tag="nav">

    <sec:ifAllGranted roles='${br.ufscar.dc.fisio.Administrador.AUTHORITY}'>
        <li><g:link controller="administrador">Administrador</g:link></li>
        <li><g:link controller="secretario">Secretário</g:link></li>
    </sec:ifAllGranted>

    <sec:ifAllGranted roles='${br.ufscar.dc.fisio.Secretario.AUTHORITY}'>
        <li><g:link controller="paciente">Paciente</g:link></li>
        <li><g:link controller="consulta">Consulta</g:link></li>
        <li><g:link controller="ficha">Ficha</g:link></li>
        <li><g:link controller="fisioterapeuta">Fisioterapeuta</g:link></li>
    </sec:ifAllGranted>
    <sec:ifAllGranted roles='${br.ufscar.dc.fisio.Fisioterapeuta.AUTHORITY}'>
        <li><g:link controller="avaliacao">Avaliação</g:link></li>
        <li><g:link controller="consulta">Consulta</g:link></li>
        <li><g:link controller="relatorio">Relatório</g:link></li>
    </sec:ifAllGranted>

    <sec:ifLoggedIn>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">
                Logado como <sec:username/>
                <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
                <li class="controller">
                    <g:link controller="logout">Logout</g:link>
                </li>
            </ul>
        </li>
    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>
        <li class="dropdown">
            <g:link controller="login">Login</g:link>
        </li>
    </sec:ifNotLoggedIn>
</content>

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Bem-vindo à Clínica</h1>
    </section>
</div>

</body>
</html>
