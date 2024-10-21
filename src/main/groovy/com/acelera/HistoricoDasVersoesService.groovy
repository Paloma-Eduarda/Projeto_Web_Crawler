package com.acelera

import org.jsoup.nodes.Document

import javax.xml.bind.Element

import static groovyx.net.http.HttpBuilder.configure


class HistoricoDasVersoesService {

    void acessarCampoHistorico(urlTISS){

        Document paginaHistorico = configure {
            request.uri = urlTISS
        }.get()

        paginaHistorico

        def CampoHistorico = paginaHistorico.getElementsContainingOwnText("Clique aqui para acessar todas as versões dos Componentes")

        if (CampoHistorico) {
            def linkElement = CampoHistorico.first().select("a").first()
            if(linkElement){
                String urlHistotico = linkElement.absUrl('href')
                coletaDadosHistorico(urlHistotico)

            }else{
                println "Erro"
            }
        } else {
            println "O novo campo não foi encontrado na página."
        }
    }

    void coletaDadosHistorico(urlHistotico){

        Document pagina = configure {
            request.uri = urlHistotico
        }.get()

        def tabela = pagina.getElementsByTag("table").first()
        tabela.getElementsByTag("tr").first().remove()

        List<HistoricoDasVersoes> historico = []

        def elementos = tabela.getElementsByTag("tr")
        elementos.each {elemento ->

            String competencia = elemento.getElementsByTag("td").get(0).text()
            String vigencia = elemento.getElementsByTag("td").get(1).text()
            String publicacao = elemento.getElementsByTag("td").get(2).text()

            historico.add(
                    new HistoricoDasVersoes(
                            competencia,
                            vigencia,
                            publicacao
                    )
            )


        }
        println historico
    }
}
