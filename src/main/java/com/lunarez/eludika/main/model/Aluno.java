package com.lunarez.eludika.main.model;

import java.util.Date;
import java.util.List;

public class Aluno extends Usuario {
    
    // ------------------------------------------------------------------ Campos
    private int idAluno;
    
    
    // ------------------------------------------------------------ Construtores
    public Aluno() {
        
        super();
        idAluno = 0;
    }
    
    public Aluno(int idUsuario, String primeiroNome, String sobrenome, 
            String email, String senha, Date nascimento, int idAluno,
            String tipo, List<Jogo> jogos) {
        
        super(idUsuario, primeiroNome, sobrenome, email, senha, nascimento, tipo, jogos);
        this.idAluno = idAluno;
    }
    
    // ------------------------------------------------------- Getters e Setters
    public int getIdAluno() {
        
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        
        this.idAluno = idAluno;
    }    
}
