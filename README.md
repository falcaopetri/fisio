# Descrição do Trabalho


Nosso projeto visa atender as necessidades reais da Clinica de Fisioterapia do Centro Universitário Barão de Mauá que atualmente tem seus procedimentos e fichas arquivados de maneira não digital.

O sistema funciona com três papéis de autenticação diferentes: administrador, secretário e fisioterapeuta. O administrador tem como função apenas cadastrar um secretário. O secretário tem como função o cadastro das informações de um paciente, sua ficha, do fisioterapeuta e das consultas. Por fim o fisioterapeuta tem como função o cadastro dos relatórios das consultas e das avaliações de lesão.

O projeto foi desenvolvido utilizando o framework Grails. O diagrama de classes e o diagrama de casos de uso pode ser visto no GitHub.

## Rodando o projeto
### Grails
O projeto foi desenvolvido usando o Grails 3.1.4. Para executá-lo, o processo mais simples é utilizar o IntelliJ Ultimate Edition.

### Heroku
O projeto foi hospedado no Heroku. Além de rodar o projeto manualmente, é possível acessá-lo na URL [fisio.herokuapp.com](https://fisio.herokuapp.com/). A política deles é suspender instâncias que fiquem idle por 30 minutos. Assim, durante o primeiro acesso ao site, é possível que uma página 404 seja retornada. O processo de boot da instância e do projeto Grails pode demorar até 2 minutos. Atualize a página até ser bem sucedido.

<img src="docs/diagrama_casos_de_uso.png">
<img src="docs/diagrama_classes.png">

