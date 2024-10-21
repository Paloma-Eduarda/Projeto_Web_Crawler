package com.acelera

class HistoricoDasVersoes {

    private String competencia
    private String publicacao
    private String inicioVigencia

    HistoricoDasVersoes(String competencia, String inicioVigencia, String publicacao) {
        this.competencia = competencia
        this.inicioVigencia = inicioVigencia
        this.publicacao = publicacao
    }

    String getCompetencia() {
        return competencia
    }

    void setCompetencia(String competencia) {
        this.competencia = competencia
    }

    String getInicioVigencia() {
        return inicioVigencia
    }

    void setInicioVigencia(String inicioVigencia) {
        this.inicioVigencia = inicioVigencia
    }

    String getPublicacao() {
        return publicacao
    }

    void setPublicacao(String publicacao) {
        this.publicacao = publicacao
    }


    @Override
    public String toString() {
        return "\ncompetencia='" + competencia + '\'' +
                ", publicacao='" + publicacao + '\'' +
                ", inicioVigencia='" + inicioVigencia + '\'';
    }
}
