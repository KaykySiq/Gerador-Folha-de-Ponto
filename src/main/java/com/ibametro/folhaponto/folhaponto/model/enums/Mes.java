package com.ibametro.folhaponto.folhaponto.model.enums;

public enum Mes {
    JANEIRO("Janeiro"),
    FEVEREIRO("Fevereiro"),
    MARCO("Março"),
    ABRIL("Abril"),
    MAIO("Maio"),
    JUNHO("Junho"),
    JULHO("Julho"),
    AGOSTO("Agosto"),
    SETEMBRO("Setembro"),
    OUTUBRO("Outubro"),
    NOVEMBRO("Novembro"),
    DEZEMBRO("Dezembro");

    private final String mesRef;

    Mes(String mesRef) {
        this.mesRef = mesRef;
    }

    public String getMesRef() {
        return mesRef;
    }
}
