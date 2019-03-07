package com.lunarez.eludika.main.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {
    
    // ------------------------------------------------------------------ Campos   
    private int idUsuario;
    private String primeiroNome;
    private String sobrenome;
    private String email;
    private String senha;
    private Date nascimento;
    private String tipo;
    private List<Jogo> jogos;
    
    
    // ------------------------------------------------------------ Construtores 
    public Usuario() {
        
        idUsuario = 0;
        primeiroNome = new String();
        sobrenome = new String();
        email = new String();
        senha = new String();
        nascimento = new Date();
        tipo = new String();
        jogos = new ArrayList<>();
    }

    public Usuario(int idUsuario, String primeiroNome, String sobrenome, 
            String email, String senha, Date nascimento, String tipo, 
            List<Jogo> jogos) {
        
        this.idUsuario = idUsuario;
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.nascimento = nascimento;
        this.tipo = tipo;
        this.jogos = jogos;
    }
    
    
    // ------------------------------------------------------- Getters e Setters 
    public int getIdUsuario() {
           
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        
        this.idUsuario = idUsuario;
    }

    public String getPrimeiroNome() {
        
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        
        this.primeiroNome = primeiroNome;
    }

    public String getSobrenome() {
        
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        
        this.sobrenome = sobrenome;
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

    public Date getNascimento() {
        
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        
        this.nascimento = nascimento;
    }
    
    public String getTipo() {
        
        return tipo;
    }

    public void setTipo(String tipo) {
        
        this.tipo = tipo;
    }

    public List<Jogo> getJogos() {
        
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        
        this.jogos = jogos;
    }  
}
