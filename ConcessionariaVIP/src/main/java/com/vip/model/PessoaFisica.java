package com.vip.model;

public class PessoaFisica extends Cliente {
    private String cpf;

    public PessoaFisica(String nome, String contato, String cpf) {
        super(nome, contato);
        this.cpf = cpf;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    @Override
    public String exibirDados() {
        return "PF: " + getNome() + " | CPF: " + cpf + " | Contato: " + getContato();
    }
}
