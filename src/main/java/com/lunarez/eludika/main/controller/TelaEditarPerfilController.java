package com.lunarez.eludika.main.controller;

import com.lunarez.eludika.main.model.response.AutenticacaoResponseModel;
import com.lunarez.eludika.main.model.response.UsuarioResponseModel;
import com.lunarez.eludika.main.model.response.ews.UsuariosEWS;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

/**
 * Classe que faz o controle da tela onde são efetivamente carregados os amigos
 *
 * @author eres
 */
public class TelaEditarPerfilController extends ControllerEludika implements Initializable {

    // ------------------------------------------------------------------ Campos
    private static TelaEditarPerfilController controller;
    
    @FXML 
    Circle circleImagemPerfil;
    
    String imagemPerfil;
    
    @FXML 
    TextField textFieldNomeCompleto;
    
    @FXML 
    TextField textFieldBiografia;
    
    @FXML 
    TextField textFieldCidade;
    
    @FXML 
    TextField textFieldEstado;
    
    @FXML
    TextField textFieldEmail;
    
    UsuariosEWS usuarioEWS;
    
    
    public static TelaEditarPerfilController getController() {
        
        return TelaEditarPerfilController.controller;
    }
    
    
    // ----------------------------------------------------------------- Métodos
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TelaEditarPerfilController.controller = this;
                
        this.usuarioEWS = new UsuariosEWS();
        
        preencherCampos();
    }    
    
    @FXML
    public void exibirPopUpTrocarAvatar() throws IOException {
        
        super.exibirPopUp("/fxml/TelaAvatar.fxml",
                TelaPrincipalController.getController().stakePane,
                TelaPrincipalController.getController().anchorPaneSombra);
    }  
    
    @FXML
    public void cancelar() throws IOException {
        
        super.chamarTelaInterna("/fxml/TelaPerfil.fxml", 
                TelaPrincipalController.getController().stakePane,
                TelaPrincipalController.getController().scrollPaneConteudo, 
                TelaPrincipalController.getController().anchorPaneSombra);
    } 
    
    @FXML
    public void salvarInformacoes() throws IOException {
            
        UsuarioResponseModel usuarioResponseModel = this.usuarioEWS
                .atualizarUsuario(AutenticacaoResponseModel.getSessao().getIdExterno(),
                        AutenticacaoResponseModel.getSessao().getToken(),
                        this.imagemPerfil, this.textFieldNomeCompleto.getText(),
                        this.textFieldBiografia.getText(), this.textFieldCidade.getText(),
                        this.textFieldEstado.getText(), this.textFieldEmail.getText());
              
        super.chamarTelaInterna("/fxml/TelaPerfil.fxml", 
                TelaPrincipalController.getController().stakePane,
                TelaPrincipalController.getController().scrollPaneConteudo, 
                TelaPrincipalController.getController().anchorPaneSombra);  
        
        super.arredondarImagem("/images/perfil/avatars/" + this.imagemPerfil 
                + ".png", TelaPrincipalController.getController().circleImagemPerfil);
    } 
    
    public void preencherCampos() {
        
        /* Processo de comunicação com o web service */
        UsuarioResponseModel resposta = this.usuarioEWS.obterUsuario(
                AutenticacaoResponseModel.getSessao().getIdExterno(),
                AutenticacaoResponseModel.getSessao().getToken());
        
        /* Processo de preenchimento das informações na tela */
        super.arredondarImagem("/images/perfil/avatars/" 
                + resposta.getImagemPerfil() + ".png", this.circleImagemPerfil);
        this.imagemPerfil = resposta.getImagemPerfil();
        
        this.textFieldNomeCompleto.setText(resposta.getNomeCompleto());
        
        this.textFieldEmail.setText(resposta.getEmail());
    
        if(resposta.getBiografia() != null && !resposta.getBiografia().isEmpty()) {
            
            this.textFieldBiografia.setText(resposta.getBiografia());
        }
        
        if(resposta.getCidade()!= null && !resposta.getCidade().isEmpty()) {
            
            this.textFieldCidade.setText(resposta.getCidade());
        }
        
        if(resposta.getEstado()!= null && !resposta.getEstado().isEmpty()) {
            
            this.textFieldEstado.setText(resposta.getEstado());
        }  
    }
}
