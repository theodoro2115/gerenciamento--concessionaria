package com.manus.concessionaria.model;

public class Moto extends Veiculo {
    public Moto(String modelo, String fabricante, int ano) {
        super(modelo, fabricante, ano, "moto");
    }

    @Override
    public String exibirInformacoes() {
        return "🏍️ MOTO: " + getFabricante() + " " + getModelo() + " (" + getAno() + ") - " + getStatus().toUpperCase();
    }
}
