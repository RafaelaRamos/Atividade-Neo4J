/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Connection.DriverFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Publicacao;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import static org.neo4j.driver.v1.Values.parameters;

/**
 *
 * @author Cliente
 */
public class PublicacaoDao implements AutoCloseable {

    private Driver driver;
    private Session session;

    public PublicacaoDao() {

        driver = new DriverFactory().getDriver();
        session = driver.session();

    }

    //Criar publicação
    public boolean criarPublicacao(String email, Publicacao p) {

        int cont = 0;
        try (Transaction tx = session.beginTransaction()) {

            StatementResult result = tx.run("Match(u:Usuario)where u.email= $email "
                    + "CREATE(u)-[r:posta]->(p:Publicacao{id:$id,texto:$texto,dataHora:$dataHora})",
                    parameters("id", p.getId(), "email", email, "texto", p.getTexto(),
                            "dataHora", p.getDataHora()));

            cont = result.summary().counters().nodesCreated();
            tx.success();

        }
        return cont > 0;
    }

    //Deletar Publicação
    public boolean Deletar(int id) {
        int cont = 0;
        try (Transaction tx = session.beginTransaction()) {
            StatementResult result = tx.run("MATCH (p:Publicacao) WHERE p.id =$id "
                   + " DETACH DELETE p", parameters("id", id));
            cont = result.summary().counters().nodesDeleted();
            tx.success();
        }
        return cont > 0;
    }

    //Listar publicações dado um determinado usuário
    public List ListarPublicacao(String email) {

        try (Transaction tx = session.beginTransaction()) {

            List publicacoes = new ArrayList();
            StatementResult result = tx.run("MATCH (u:Usuario)-[:posta]->(p:Publicacao) "
                    + "WHERE u.email = $email "
                    + "RETURN  p.texto ,p.dataHora", parameters("email", email));

            while (result.hasNext()) {
                Record record = result.next();

                String texto = record.get("p.texto").asString();
                LocalDateTime dataHora = record.get("p.dataHora").asLocalDateTime();
                Publicacao p = new Publicacao(texto, dataHora);
                publicacoes.add(p);
                tx.success();

            }
            return publicacoes;

        }

    }

    @Override
    public void close() throws Exception {
        session.close();
        driver.close();

    }
}
