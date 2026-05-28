package com.vip.model;

public class Utilitario extends Veiculo {
    public Utilitario(String modelo, String fabricante, int ano) {
        super(modelo, fabricante, ano, "utilitário");
    }

    @Override
    public String exibirInformacoes() {
        return "🚚 UTILITÁRIO: " + getFabricante() + " " + getModelo() + " | ANO: " + getAno() + " | STATUS: " + getStatus().toUpperCase();
    }
}
