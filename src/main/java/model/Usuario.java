/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Cliente
 */
public class Usuario {

    private String email;
    private String senha;
    private String nome;
    private String cidade;
    private LocalDate nascimento;

    public Usuario() {
    }

    public Usuario(String email, String senha, String nome, String cidade, LocalDate nascimento) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cidade = cidade;
        this.nascimento = nascimento;
    }

   

    public Usuario(String email, String nome,String cidade, LocalDate nascimento) {
       this.nome = nome;
         this.cidade = cidade;
        this.nascimento = nascimento;
         this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate Nascimento) {
        this.nascimento = Nascimento;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", senha=" + senha + ", nome=" + nome + ", cidade=" + cidade + ", nascimento=" + nascimento + '}';
    }

   

}
