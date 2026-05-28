package com.vip.model;

public class Carro extends Veiculo {
    public Carro(String modelo, String fabricante, int ano) {
        super(modelo, fabricante, ano, "carro");
    }

    @Override
    public String exibirInformacoes() {
        return "🚗 CARRO: " + getFabricante() + " " + getModelo() + " [" + getAno() + "] - " + getStatus().toUpperCase();
    }
}
