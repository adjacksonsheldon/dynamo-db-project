package com.sheldon.dynamodb.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.sheldon.dynamodb.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteService {

    private final AmazonDynamoDB amazonDynamoDB;
    private final DynamoDBMapper dynamoDBMapper;
    private final CriarTabelaClienteService criarTabelaClienteService;

    public ClienteService(CriarTabelaClienteService criarTabelaClienteService) {
        this.amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build();
        this.dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        this.criarTabelaClienteService = criarTabelaClienteService;
    }

    public void criarTabela(){
        criarTabelaClienteService.criar();
    }

    public void criarOuAtualizar(Cliente cliente){
        dynamoDBMapper.save(cliente);
    }

    public Cliente consultarPorChavePrimaria(String email){
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        return dynamoDBMapper.load(Cliente.class, email);
    }

    public void deletar(String email){
        Cliente cliente = new Cliente();
        cliente.setEmail(email);

        dynamoDBMapper.delete(cliente);
    }

    public List<Cliente> consultarMaioresQue34Anos(){
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":n", new AttributeValue().withN("34"));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("idade > :n")
                .withExpressionAttributeValues(eav);

        return dynamoDBMapper.scan(Cliente.class, scanExpression);
    }
}
