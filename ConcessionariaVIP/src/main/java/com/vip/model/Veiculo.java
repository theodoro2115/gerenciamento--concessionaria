package com.vip.model;

public abstract class Veiculo {
    private String modelo;
    private String fabricante;
    private int ano;
    private String tipo;
    private String status;
    private Cliente comprador;
    private String dataVenda;

    public Veiculo(String modelo, String fabricante, int ano, String tipo) {
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.ano = ano;
        this.tipo = tipo;
        this.status = "disponível";
    }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Cliente getComprador() { return comprador; }
    public void setComprador(Cliente comprador) { this.comprador = comprador; }
    public String getDataVenda() { return dataVenda; }
    public void setDataVenda(String dataVenda) { this.dataVenda = dataVenda; }

    public abstract String exibirInformacoes();
}
