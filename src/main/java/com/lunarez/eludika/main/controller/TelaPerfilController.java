package com.lunarez.eludika.main.controller;

import com.lunarez.eludika.main.model.response.AutenticacaoResponseModel;
import com.lunarez.eludika.main.model.response.UsuarioResponseModel;
import com.lunarez.eludika.main.model.response.ews.UsuariosEWS;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

/**
 * Classe que faz o controle da tela do perfil do jogador
 *
 * @author eres
 */
public class TelaPerfilController extends ControllerEludika implements Initializable {
    
    // --------------------------------------------- Sessão: Informações Básicas
    UsuariosEWS usuarioEWS;
            
    @FXML
    Circle circleImagemPerfil;
    
    @FXML
    Label labelCodinome;
    
    @FXML
    Label labelNomeCompleto;
    
    @FXML
    Label labelBiografia;
    
    @FXML
    Label labelNivel;
    
    @FXML
    Label labelPontos;
    
    // ---------------------------------------------- Sessão: Maiores Conquistas
    @FXML
    Circle circleConquista1;
    
    @FXML
    Circle circleConquista2;
    
    @FXML
    Circle circleConquista3;
    
    @FXML
    Circle circleConquista4;
    
    @FXML
    Label labelConquista1;
    
    @FXML
    Label labelConquista2;
    
    @FXML
    Label labelConquista3;
    
    @FXML
    Label labelConquista4;
    
    @FXML
    Label labelDescricaoConquista1;
    
    @FXML
    Label labelDescricaoConquista2;
    
    @FXML
    Label labelDescricaoConquista3;
    
    @FXML
    Label labelDescricaoConquista4;
    
    // ----------------------------------------------------------- Sessão: Jogos
    @FXML
    Button botaoJogos;
    
    @FXML
    Circle circleJogo1;
    
    @FXML
    Circle circleJogo2;
    
    @FXML
    Circle circleJogo3;
    
    @FXML
    Button botaoJogo1;
    
    @FXML
    Button botaoJogo2;
    
    @FXML
    Button botaoJogo3;
    
    // ---------------------------------------------------------- Sessão: Amigos
    @FXML
    Button botaoAmigos;
    
    @FXML
    Circle circleAmigo1;
    
    @FXML
    Circle circleAmigo2;
    
    @FXML
    Circle circleAmigo3;
    
    @FXML
    Circle circleAmigo4;
    
    @FXML
    Circle circleAmigo5;
    
    @FXML
    Circle circleAmigo6;
    
    @FXML
    Circle circleAmigo7;
    
    @FXML
    Circle circleAmigo8;
    
    @FXML
    Circle circleAmigo9;
    
    @FXML
    Button botaoAmigo1;
    
    @FXML
    Button botaoAmigo2;
    
    @FXML
    Button botaoAmigo3;
    
    @FXML
    Button botaoAmigo4;
    
    @FXML
    Button botaoAmigo5;
    
    @FXML
    Button botaoAmigo6;
    
    @FXML
    Button botaoAmigo7;
    
    @FXML
    Button botaoAmigo8;
    
    @FXML
    Button botaoAmigo9;
    
    // ---------------------------------------------------------- Sessão: Turmas
    @FXML
    Button botaoTurmas;
    
    @FXML
    Circle circleTurma1;
    
    @FXML
    Circle circleTurma2;
    
    @FXML
    Circle circleTurma3;
    
    @FXML
    Circle circleTurma4;
    
    @FXML
    Circle circleTurma5;
    
    @FXML
    Circle circleTurma6;
    
    @FXML
    Circle circleTurma7;
    
    @FXML
    Circle circleTurma8;
    
    @FXML
    Circle circleTurma9;
    
    @FXML
    Button botaoTurma1;
    
    @FXML
    Button botaoTurma2;
    
    @FXML
    Button botaoTurma3;
    
    @FXML
    Button botaoTurma4;
    
    @FXML
    Button botaoTurma5;
    
    @FXML
    Button botaoTurma6;
    
    @FXML
    Button botaoTurma7;
    
    @FXML
    Button botaoTurma8;
    
    @FXML
    Button botaoTurma9;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /* Processo de comunicação com o web service */
        this.usuarioEWS = new UsuariosEWS();
        
        UsuarioResponseModel resposta = this.usuarioEWS.obterUsuario(
                AutenticacaoResponseModel.getSessao().getIdExterno(),
                AutenticacaoResponseModel.getSessao().getToken());
        
        /* Processo de preenchimento das informações na tela */
        super.arredondarImagem("/images/perfil/avatars/" 
                + resposta.getImagemPerfil() + ".png", circleImagemPerfil);

        this.labelCodinome.setText("@" + resposta.getCodinome());
        
        this.labelNomeCompleto.setText(resposta.getNomeCompleto());
    
        if(resposta.getBiografia() == null || resposta.getBiografia().isEmpty()) {
            
            this.labelBiografia.setText("\"Este usuário é tímido e ainda não possui uma biografia!\"");
        }
        else {
            
            this.labelBiografia.setText("\"" + resposta.getBiografia() + "\"");
        }
        
        this.labelNivel.setText(Integer.toString(resposta.getNivel()));
        
        this.labelPontos.setText("Pontos: " + resposta.getPontos() + "/" + resposta.getNivel() * 10);
        
        super.arredondarImagem("/images/perfil/conquista-desconhecida.png", circleConquista1);
        super.arredondarImagem("/images/perfil/conquista-desconhecida.png", circleConquista2);
        super.arredondarImagem("/images/perfil/conquista-desconhecida.png", circleConquista3);
        super.arredondarImagem("/images/perfil/conquista-desconhecida.png", circleConquista4);
    }    
    
    public void acessarAmigos() throws IOException {
    
        super.servicoDesejado = new String("Amigos");
        
        super.chamarTelaInterna("/fxml/TelaInternaListarUsuarios.fxml", 
            TelaPrincipalController.getController().scrollPaneConteudo);
    }
    
    public void chamarTelaEditarPerfil() throws IOException {
        
        super.chamarTelaInterna("/fxml/TelaEditarPerfil.fxml", 
            TelaPrincipalController.getController().scrollPaneConteudo);
    }   
}
