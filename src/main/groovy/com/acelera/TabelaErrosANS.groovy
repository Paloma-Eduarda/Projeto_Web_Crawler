package com.acelera

import groovyx.net.http.optional.Download
import org.jsoup.nodes.Document

import static groovyx.net.http.HttpBuilder.configure

class TabelaErrosANS {

    void acessarCampoTabela(urlTabelaRelacionada){

        Document paginaTabela = configure {
            request.uri = urlTabelaRelacionada
        }.get()


        def CampoTB = paginaTabela.getElementsContainingOwnText("Clique aqui para baixar a tabela de erros no envio para a ANS (.xlsx)")

        if (CampoTB) {
            def linkElement = CampoTB.first().select("a").first()
            if(linkElement){
                String urlTb = linkElement.absUrl('href')
                println "Link para tabelas relacionadas ${urlTb} "
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
        }
    }

}
