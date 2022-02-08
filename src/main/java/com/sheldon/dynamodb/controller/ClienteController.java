package com.sheldon.dynamodb.controller;

import com.sheldon.dynamodb.model.Cliente;
import com.sheldon.dynamodb.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "criarTabela")
    public String incluir() {
        this.clienteService.criarTabela();
        return "Tabela cliente criada com sucesso!";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String criarOuAtualizar(@RequestBody Cliente cliente) {
        this.clienteService.criarOuAtualizar(cliente);
        return "Cliente incluído com sucesso!";
    }

    @RequestMapping(method = RequestMethod.GET)
    public Cliente consultarPorChavePrimaria(@RequestParam String email) {
        return this.clienteService.consultarPorChavePrimaria(email);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deletar(@RequestParam String email) {
        this.clienteService.deletar(email);
        return "Cliente incluído com sucesso!";
    }

    @RequestMapping(value = "scan")
    public List<Cliente> consultarMaioresQue34Anos() {
        return this.clienteService.consultarMaioresQue34Anos();
    }
}
