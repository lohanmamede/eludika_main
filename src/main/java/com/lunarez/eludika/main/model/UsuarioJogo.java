package com.lunarez.eludika.main.model;

public class UsuarioJogo {
    
    // ------------------------------------------------------------------ Campos
    private Usuario usuario;
    private Jogo jogo;
    private int experiencia;
    private int nivel;
    private int faseAtual;
    private int moedas;
    
    
    // ------------------------------------------------------------ Construtores
    public UsuarioJogo() {
        
        usuario = new Usuario();
        jogo = new Jogo();
        experiencia = 0;
        nivel = 0;
        faseAtual = 0;
        moedas = 0;
    }
    
    public UsuarioJogo(Usuario usuario, Jogo jogo, int experiencia, int nivel, 
            int faseAtual, int moedas) {
        
        this.usuario = usuario;
        this.jogo = jogo;
        this.experiencia = experiencia;
        this.nivel = nivel;
        this.faseAtual = faseAtual;
        this.moedas = moedas;
    }
    
       
    // ------------------------------------------------------- Getters e Setters
    public Usuario getUsuario() {
        
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        
        this.usuario = usuario;
    }

    public Jogo getJogo() {
        
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        
        this.jogo = jogo;
    }

    public int getExperiencia() {
        
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        
        this.experiencia = experiencia;
    }

    public int getNivel() {
        
        return nivel;
    }

    public void setNivel(int nivel) {
        
        this.nivel = nivel;
    }

    public int getFaseAtual() {
        
        return faseAtual;
    }

    public void setFaseAtual(int faseAtual) {
        
        this.faseAtual = faseAtual;
    }

    public int getMoedas() {
        
        return moedas;
    }

    public void setMoedas(int moedas) {
        
        this.moedas = moedas;
    }  
}