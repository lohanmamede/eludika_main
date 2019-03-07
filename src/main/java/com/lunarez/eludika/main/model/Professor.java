package com.lunarez.eludika.main.model;

import java.util.Date;
import java.util.List;

public class Professor extends Usuario {
    
    // ------------------------------------------------------------------ Campos  
    private int idProfessor;
    
    
    // ------------------------------------------------------------ Construtores
    public Professor() {
        
        super();
        idProfessor = 0;
    }
    
    public Professor(int idUsuario, String primeiroNome, String sobrenome, 
            String email, String senha, Date nascimento, int idProfessor,
            String tipo, List<Jogo> jogos) {
        
        super(idUsuario, primeiroNome, sobrenome, email, senha, nascimento, tipo, jogos);
        this.idProfessor = idProfessor;
    }
    
    
    // ------------------------------------------------------- Getters e Setters
    public int getIdProfessor() {
        
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        
        this.idProfessor = idProfessor;
    }
}
