/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import model.Usuario;
import Connection.DriverFactory;
import java.time.LocalDate;
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
public class UsuarioDao implements AutoCloseable {

    private Driver driver;
    private Session session;

    public UsuarioDao() {

        driver = new DriverFactory().getDriver();
        session = driver.session();

    }
//Salvar usuário

    public boolean salvar(Usuario usuario) {
        int cont = 0;
        try (Transaction tx = session.beginTransaction()) {

            StatementResult result = tx.run(
                    "Create (:Usuario{email:$email,senha:$senha,nome:$nome,cidade:$cidade,nascimento:$nascimento})",
                    parameters("email", usuario.getEmail(), "senha", usuario.getSenha(),
                            "nome", usuario.getNome(), "cidade",usuario.getCidade(), "nascimento", usuario.getNascimento()));
            cont = result.summary().counters().nodesCreated();
            tx.success();

        }
        return cont > 0;
    }

    //Deletar usuário
    public boolean Deletar(String email) {
        int cont = 0;
        try (Transaction tx = session.beginTransaction()) {
            StatementResult result = tx.run("MATCH (u:Usuario) WHERE u.email= $email"
                    
                    + " DETACH DELETE u", parameters("email", email));
            cont = result.summary().counters().nodesDeleted();
            tx.success();
        }
        return cont > 0;
    }
    //Atualizar usuário

    public boolean Atualizar(Usuario u) {
        int cont = 0;

        try (Transaction tx = session.beginTransaction()) {
            StatementResult result = tx.run("Match(u:Usuario)where u.email=$email"
                    + " SET u.senha= $senha, u.nome =$nome,u.cidade=$cidade, u.nascimento =$nascimento",
                    parameters("email", u.getEmail(), "senha", u.getSenha(),
                            "nome", u.getNome(), "cidade", u.getCidade(), "nascimento", u.getNascimento()));
            cont = result.summary().counters().propertiesSet();
            tx.success();
        }
        return cont > 0;
    }

//Buscar Usuario pelo email
    public Usuario BuscarPorEmail(String email) {
        try (Transaction tx = session.beginTransaction()) {
            StatementResult result = tx.run("Match(u:Usuario) where u.email = $email "
                    + "return u.nome,u.cidade,u.nascimento,u.senha",
                    parameters("email", email));
            if (result.hasNext()) {
                Record record = result.next();

                String nome = record.get("u.nome").asString();
                String cidade = record.get("u.cidade").asString();
                LocalDate nascimento = record.get("u.nascimento").asLocalDate();

                return new Usuario(email, nome, cidade, nascimento);
            } else {
                return null;
            }
        }

    }
//Seguir usuario

    public boolean SeguirUsuario(String seguidor, String usuario) {
        int cont = 0;
        try (Transaction tx = session.beginTransaction()) {
            StatementResult result = tx.run("MATCH (u1:Usuario),(u2:Usuario)"
                    + " WHERE u1.email=$seguidor and u2.email=$usuario"
                    + " CREATE (u1)-[s:Seguindo]->(u2)",
                    parameters("seguidor", seguidor, "usuario", usuario));
            cont = result.summary().counters().relationshipsCreated();
            tx.success();
        }
        return cont > 0;
    }

//Listar quais usuarios que eu estou seguindo está seguindo.
    public List SeguidoresDeSeguidores(String email) {

        try (Transaction tx = session.beginTransaction()) {

            List seguidores = new ArrayList();
            StatementResult result = tx.run("MATCH (u:Usuario)-[:Seguindo]->(:Usuario)-[:Seguindo]->(seguidor:Usuario) "
                    + "WHERE u.email = $email "
                    + "RETURN  DISTINCT seguidor.email,seguidor.nome,seguidor.cidade,seguidor.nascimento", parameters("email", email));

            while (result.hasNext()) {
                Record record = result.next();
                String emailSeguidor = record.get("seguidor.email").asString();
                String nome = record.get("seguidor.nome").asString();
                String cidade = record.get("seguidor.cidade").asString();
                LocalDate nascimento = record.get("seguidor.nascimento").asLocalDate();
                Usuario u = new Usuario(emailSeguidor, nome, cidade, nascimento);
                seguidores.add(u);
                tx.success();

            }
            return seguidores;

        }

    }

    
    @Override
    public void close() throws Exception {
        session.close();
        driver.close();
    }

}
