package br.com.clientescrud.model;

import javax.persistence.*;

@Entity
@Table(name = "USUARIO_TABELA")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;
    private String email;
    private String username;
    private String senha;

    public Usuario(){}

    public Usuario(String nome1){
        this.nome = nome1;
    }
    public Usuario(String nome1, String email1){
        this.nome = nome1;
        this.email = email1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome1){
        this.nome = nome1;
    }

    public Long getId() {
        return id;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
