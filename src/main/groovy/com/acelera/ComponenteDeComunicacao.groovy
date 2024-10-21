package com.acelera

import groovyx.net.http.optional.Download
import org.jsoup.nodes.Document

import static groovyx.net.http.HttpBuilder.configure

class ComponenteDeComunicacao {


    void acessarCampoPTSetembro(urlTISS){

        Document paginaTISS = configure {
            request.uri = urlTISS
        }.get()

        String urlPT

        def CampoPTSet = paginaTISS.getElementsContainingOwnText("Clique aqui para acessar a versão Setembro/2024")

        if (CampoPTSet) {
            def linkElement = CampoPTSet.first().select("a").first()

            if(linkElement){
                urlPT = linkElement.absUrl('href')
                acessarCampoComponenteComunicacao(urlPT)
            }else{
                println "Erro"
            }
        } else {
            println "O novo campo não foi encontrado na página."
        }
    }
    void acessarCampoComponenteComunicacao(urlPT){

        Document pagina = configure {
            request.uri = urlPT
        }.get()

        def tabela = pagina.getElementsByTag("table").first()
        tabela.getElementsByTag("tr").first().remove()

        def elementos = tabela.getElementsByTag("tr")
        def celulas
        elementos.each {elemento ->

            if(elemento.text().contains("Componente de Comunicação")){
                 celulas = elemento.getElementsContainingOwnText("Baixar")
            }
        }

        def linkElement = celulas.first().select("a").first()
        if(linkElement){
            String url = linkElement.absUrl('href')
           baixarComponenteComunicacao(url)

        }else{
            println "Erro"
        }
    }

    void baixarComponenteComunicacao(url){

        File saved = new File('/home/paloma/IdeaProjects/Projeto_Web_Crawler/recursos/componentesComunicacao.zip')

        configure {
            request.uri = url
        }.get {
            Download.toFile(delegate, saved)
            println "Pasta salva com sucesso"
        }
    }
}
