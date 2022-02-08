package com.sheldon.dynamodb.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CriarTabelaClienteService {
    public void criar(){
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDB dynamoDB = new DynamoDB(client);
        String tableName = "Clientes";

        try {
            System.out.println("Attempting to create table; please wait...");

            Table table = dynamoDB.createTable(tableName, listaSchemas(), listaDefinicaoAtributos(), getProvisionedThroughput());
            table.waitForActive();

            System.out.println("Success. Table status: " +
                    table.getDescription().getTableStatus());

        } catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
        }
    }

    private ProvisionedThroughput getProvisionedThroughput() {
        return new ProvisionedThroughput(10L, 10L);
    }

    private List<AttributeDefinition> listaDefinicaoAtributos() {
        return Arrays.asList(
                new AttributeDefinition("email", ScalarAttributeType.S)
        );
    }

    private List<KeySchemaElement> listaSchemas() {
        return Arrays.asList(
                new KeySchemaElement("email", KeyType.HASH)
        );
    }
}
