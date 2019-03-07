package com.lunarez.eludika.main.model.response;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class UsuarioTemJogoResponseModel {

    // ------------------------------------------------------------------ Campos
    private String idExternoUsuario;
    private String idExternoJogo;
    private int pontos;
    private int nivel;
    private int moedas;
    private boolean jogoPreferido;
    

    // ------------------------------------------------------- Getters e Setters
    public String getIdExternoUsuario() {
        
        return idExternoUsuario;
    }

    public void setIdExternoUsuario(String idExternoUsuario) {
        
        this.idExternoUsuario = idExternoUsuario;
    }

    public String getIdExternoJogo() {
        
        return idExternoJogo;
    }

    public void setIdExternoJogo(String idExternoJogo) {
        
        this.idExternoJogo = idExternoJogo;
    }

    public int getPontos() {
        
        return pontos;
    }

    public void setPontos(int pontos) {
        
        this.pontos = pontos;
    }

    public int getNivel() {
        
        return nivel;
    }

    public void setNivel(int nivel) {
        
        this.nivel = nivel;
    }

    public int getMoedas() {
        
        return moedas;
    }

    public void setMoedas(int moedas) {
        
        this.moedas = moedas;
    }

    public boolean isJogoPreferido() {
        
        return jogoPreferido;
    }

    public void setJogoPreferido(boolean jogoPreferido) {
        
        this.jogoPreferido = jogoPreferido;
    }
}