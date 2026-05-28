package com.vip.model;

public class PessoaJuridica extends Cliente {
    private String cnpj;

    public PessoaJuridica(String nome, String contato, String cnpj) {
        super(nome, contato);
        this.cnpj = cnpj;
    }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    @Override
    public String exibirDados() {
        return "PJ: " + getNome() + " | CNPJ: " + cnpj + " | Contato: " + getContato();
    }
}
