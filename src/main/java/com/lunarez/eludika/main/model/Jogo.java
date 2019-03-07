package com.lunarez.eludika.main.model;

import java.util.ArrayList;
import java.util.List;

public class Jogo {
    
    // ------------------------------------------------------------------ Campos
    private int idJogo;
    private String nome;
    private List<Usuario> usuarios;
    
    // ------------------------------------------------------------ Construtores
    public Jogo() {
        
        idJogo = 0;
        nome = new String();
        usuarios = new ArrayList<>();
    }

    public Jogo(int idJogo, String nome, List<Usuario> usuarios) {
        
        this.idJogo = idJogo;
        this.nome = nome;
        this.usuarios = usuarios;
    }
    
    
    // ------------------------------------------------------- Getters e Setters
    public int getIdJogo() {
        
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        
        this.idJogo = idJogo;
    }

    public String getNome() {
        
        return nome;
    }

    public void setNome(String nome) {
        
        this.nome = nome;
    } 

    public List<Usuario> getUsuarios() {
        
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        
        this.usuarios = usuarios;
    }
}
