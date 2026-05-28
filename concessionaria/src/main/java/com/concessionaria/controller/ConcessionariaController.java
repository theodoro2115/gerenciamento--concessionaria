/**GABRIEL THEODORO FRANCISCO - RA 2526101336
FABRICIO ARAGÃO DE SOUZA JUNIOR - RA 425106444
GUSTAVO MORAIS MORIYAMA - RA 425104831
MAYCON ALVES DA SILVA - RA 425104110 
*/


package com.manus.concessionaria.controller;

import com.manus.concessionaria.model.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api")
public class ConcessionariaController {

    private List<Veiculo> veiculos = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Map<String, String>> vendas = new ArrayList<>();

    

    @GetMapping("/veiculos")
    public List<Map<String, Object>> listarVeiculos() {
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo v = veiculos.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("id", i);
            map.put("info", v.exibirInformacoes());
            map.put("modelo", v.getModelo());
            map.put("fabricante", v.getFabricante());
            map.put("ano", v.getAno());
            map.put("tipo", v.getTipo());
            map.put("status", v.getStatus());
            map.put("comprador", v.getComprador() != null ? v.getComprador().getNome() : null);
            map.put("data_venda", v.getDataVenda());
            result.add(map);
        }
        return result;
    }

    @PostMapping("/veiculos")
    public Map<String, String> cadastrarVeiculo(@RequestBody Map<String, String> data) {
        String tipo = data.get("tipo").toLowerCase();
        String modelo = data.get("modelo");
        String fabricante = data.get("fabricante");
        int ano = Integer.parseInt(data.get("ano"));

        if (ano > 2027) throw new RuntimeException("Ano inválido! Máximo permitido é 2027.");

        Veiculo novo;
        if (tipo.equals("carro")) novo = new Carro(modelo, fabricante, ano);
        else if (tipo.equals("moto")) novo = new Moto(modelo, fabricante, ano);
        else novo = new Utilitario(modelo, fabricante, ano);

        veiculos.add(novo);
        return Collections.singletonMap("message", "Veículo cadastrado com sucesso!");
    }

    @DeleteMapping("/veiculos/{id}")
    public Map<String, String> deletarVeiculo(@PathVariable int id) {
        veiculos.remove(id);
        return Collections.singletonMap("message", "Veículo removido!");
    }


    @GetMapping("/clientes")
    public List<Map<String, Object>> listarClientes() {
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("id", i);
            map.put("info", c.exibirDados());
            map.put("nome", c.getNome());
            map.put("contato", c.getContato());
            result.add(map);
        }
        return result;
    }

    @PostMapping("/clientes")
    public Map<String, String> cadastrarCliente(@RequestBody Map<String, String> data) {
        String tipo = data.get("tipo");
        String nome = data.get("nome");
        String contato = data.get("contato");
        String documento = data.get("documento");

        Cliente novo;
        if (tipo.equals("PF")) {
            if (documento.length() != 11) throw new RuntimeException("CPF deve ter 11 dígitos.");
            novo = new PessoaFisica(nome, contato, documento);
        } else {
            novo = new PessoaJuridica(nome, contato, documento);
        }

        clientes.add(novo);
        return Collections.singletonMap("message", "Cliente cadastrado!");
    }

    @DeleteMapping("/clientes/{id}")
    public Map<String, String> deletarCliente(@PathVariable int id) {
        clientes.remove(id);
        return Collections.singletonMap("message", "Cliente removido!");
    }

    // --- VENDAS ---

    @PostMapping("/vendas")
    public Map<String, String> realizarVenda(@RequestBody Map<String, Integer> data) {
        int vId = data.get("veiculo_id");
        int cId = data.get("cliente_id");

        Veiculo v = veiculos.get(vId);
        Cliente c = clientes.get(cId);

        if (v.getStatus().equals("vendido")) throw new RuntimeException("Veículo já vendido!");

        String dataVenda = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        v.setStatus("vendido");
        v.setComprador(c);
        v.setDataVenda(dataVenda);

        Map<String, String> venda = new HashMap<>();
        venda.put("veiculo", v.getModelo());
        venda.put("cliente", c.getNome());
        venda.put("data", dataVenda);
        vendas.add(venda);

        return Collections.singletonMap("message", "Venda realizada!");
    }

    @GetMapping("/vendas")
    public List<Map<String, String>> historicoVendas() {
        return vendas;
    }
}
