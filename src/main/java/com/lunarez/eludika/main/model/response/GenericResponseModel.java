package com.lunarez.eludika.main.model.response; // :D

/** 
 * Esta classe representa o modelo de dados que será recebido do webservice quando
 * for necessário obter dados do usuário logado
 * 
 * @author eres
 */
public class GenericResponseModel {

    // ------------------------------------------------------------------ Campos
    private String operacao;
    private String status;
    

    // ------------------------------------------------------- Getters e Setters    
    public String getOperacao() {
        
        return operacao;
    }

    public void setOperacao(String operacao) {
        
        this.operacao = operacao;
    }

    public String getStatus() {
        
        return status;
    }

    public void setStatus(String status) {
        
        this.status = status;
    }
    
}