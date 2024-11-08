package com.acelera


import org.jsoup.nodes.Document

import static groovyx.net.http.HttpBuilder.configure

static void main(String[] args) {
    Scanner scanner = new Scanner(System.in)

    TabelaErrosANS tabelaErrosANS = new TabelaErrosANS()
    HistoricoDasVersoesService historico  = new HistoricoDasVersoesService()
    ComponenteDeComunicacao componente = new  ComponenteDeComunicacao()

    Document page = configure {
        request.uri = 'https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss'
    }.get()

    while (true) {
        println "\nMenu:"
        println "1. Tabela de Erros Ans"
        println "2. Historico das Versões"
        println "3. Componente de comunicação"
        println "4. Sair"
        print "Escolha uma opção: "

        int opcao = scanner.nextInt()
        scanner.nextLine()

        if (!opcao) {
            println "Entrada vazia! Por favor, digite um número."
            continue
        }

        try {
            switch(opcao) {
                case 1:
                    println "\nBaixando Tabela de Erros:"
                    tabelaErrosANS.acessarCampoTabelaRelacionada(page)
                    break
                case 2:
                    println "\nTabela Historico das versões:"
                    historico.acessarCampoHistorico(page)
                    break
                case 3:
                    println "\nBaixar Componente de Comunicação:"
                    componente.acessarCampoPTSetembro(page)
                    break
                case 4:
                    println "Saindo do programa..."
                    return
                default:
                    println "Opção inválida! Por favor, escolha uma opção válida."
            }
        } catch (NumberFormatException exception) {
            println "Entrada inválida! Por favor, insira um número. ${exception}"
        }
    }
}

