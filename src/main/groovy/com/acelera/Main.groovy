package com.acelera


import org.jsoup.nodes.Document

import static groovyx.net.http.HttpBuilder.configure

static void main(String[] args) {


    Document page = configure {
        request.uri = 'https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-historico-das-versoes-dos-componentes-do-padrao-tiss'
    }.get()

    acessarCampoPrestaServico(page)



}
def acessarCampoPrestaServico(Document document) {

    def prestadorDeServicos = document.getElementsContainingOwnText("Espaço do Prestador de Serviços de Saúde")

    if (prestadorDeServicos) {
        def linkElement = prestadorDeServicos.first().select("a").first()

        if (linkElement != null) {
            String prestadorUrl = linkElement.absUrl('href')
            acessarCampoTISS(prestadorUrl)
        } else {
            println "O link para Espaço do Prestador de Serviços de Saúde não foi encontrado."
        }
    } else {
        println "O link para espaço do prestador não foi encontrado."
    }
}
def acessarCampoTISS(prestador){
    HistoricoDasVersoesService historico  = new HistoricoDasVersoesService()
    ComponenteDeComunicacao componente = new  ComponenteDeComunicacao()

    Document paginaPrestador = configure {
        request.uri = prestador
    }.get()

    String urlTISS

    def CampoTISS = paginaPrestador.getElementsContainingOwnText("TISS - Padrão para Troca de Informação de Saúde Suplementar")

    if (CampoTISS) {
        def linkElement = CampoTISS.first().select("a").first()

        if(linkElement){
            urlTISS =linkElement.absUrl('href')

            historico.acessarCampoHistorico(urlTISS)
            acessarCampoTabelaRelacionada(urlTISS)
            componente.acessarCampoPTSetembro(urlTISS)

        }else{
            println "Erro"
        }
    } else {
        println "O novo campo não foi encontrado na página."
    }
}
def acessarCampoTabelaRelacionada(urlTISS){
    TabelaErrosANS tabelaErrosANS = new TabelaErrosANS()

    Document paginaTISS = configure {
        request.uri = urlTISS
    }.get()

    String urlTb

    def CampoTBrelacionada = paginaTISS.getElementsContainingOwnText("Clique aqui para acessar as planilhas")

    if (CampoTBrelacionada) {
        def linkElement = CampoTBrelacionada.first().select("a").first()
        if(linkElement){
            urlTb = linkElement.absUrl('href')
           tabelaErrosANS.acessarCampoTabela(urlTb)

        }else{
            println "Erro"
        }
    } else {
        println "O novo campo não foi encontrado na página."
    }
}

