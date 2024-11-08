package com.acelera

import groovyx.net.http.optional.Download
import org.jsoup.nodes.Document

import static groovyx.net.http.HttpBuilder.configure

class TabelaErrosANS {

    void acessarCampoTabelaRelacionada(urlTISS){

        String urlTb
        def CampoTBrelacionada = urlTISS.getElementsContainingOwnText("Clique aqui para acessar as planilhas")
        if (CampoTBrelacionada) {
            def linkElement = CampoTBrelacionada.first().select("a").first()
            if(linkElement){
                urlTb = linkElement.absUrl('href')
                    acessarCampoTabela(urlTb)
            }else{
                println "Erro"
            }
        } else {
            println "O novo campo não foi encontrado na página."
        }
    }

    void acessarCampoTabela(urlTabelaRelacionada){

        Document paginaTabela = configure {
            request.uri = urlTabelaRelacionada
        }.get()

        def CampoTB = paginaTabela.getElementsContainingOwnText("Clique aqui para baixar a tabela de erros no envio para a ANS (.xlsx)")

        if (CampoTB) {
            def linkElement = CampoTB.first().select("a").first()
            if(linkElement){
                String urlTb = linkElement.absUrl('href')
                baixarTabelaErrosANS(urlTb)

            }else{
                println "Erro"
            }
        } else {
            println "O novo campo não foi encontrado na página."
        }
    }

    void baixarTabelaErrosANS(urlTabelaRelacionada){
        File saved = new File('/home/paloma/IdeaProjects/Projeto_Web_Crawler/recursos/TabelaErros.xlsx')

        configure {
            request.uri = urlTabelaRelacionada
        }.get {
            Download.toFile(delegate, saved)
            println "Tabela de Erros armazenada com sucesso"
        }
    }

}
