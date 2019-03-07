package com.lunarez.eludika.main.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * Classe que faz o controle da tela dos detalhes de um jogo
 *
 * @author eres
 */
public class TelaDetalhesJogoController extends ControllerEludika implements Initializable {

    // ------------------------------------------------------------------ Campos
    @FXML
    private Label labelExperiencia;    
    @FXML
    private Label labelNivel;     
    @FXML
    private Label labelFaseAtual;      
    @FXML
    private Label labelMoedas; 
    
    // ----------------------------------------------------------------- Métodos
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
//        try {
//            
//            UsuarioJogoRequests usuarioJogoRequests = new UsuarioJogoRequests();
//            UsuarioJogo usuarioJogo = usuarioJogoRequests.recuperarUsuarioJogoRequest();
//            
//            labelExperiencia.setText(String.valueOf(usuarioJogo.getExperiencia()));
//    
//            labelNivel.setText(String.valueOf(usuarioJogo.getNivel()));
//
//            labelFaseAtual.setText(String.valueOf(usuarioJogo.getFaseAtual())); 
//
//            labelMoedas.setText(String.valueOf(usuarioJogo.getMoedas()));
//        } 
//        catch (Exception ex) {
//            
//            Logger.getLogger(TelaDetalhesJogoController.class.getName()).log(Level.SEVERE, null, ex);
//
//            Alert alert = new Alert(AlertType.ERROR, ex.toString(), ButtonType.OK);
//            alert.setTitle("Erro");
//            alert.setHeaderText("Impossível exibir as informações do jogo");
//            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
//
//            alert.showAndWait();  
//        }
    }
    
    /**
     *
     * @throws IOException
     */
    @FXML
    public void chamarTelaDetalhesJogo() throws IOException {

//        AnchorPane a = FXMLLoader.load(getClass().getResource("/fxml/TelaDetalhesJogo.fxml"));
//
//        TelaPrincipalController.getController().anchorPaneConteudo.setContent(a);
//        
//        TelaPrincipalController.getController().botaoMeusJogos.requestFocus();
    }
    
    /**
     *
     * @throws IOException
     */
    @FXML
    public void iniciarJogo() throws IOException {

        try {
            
            /* Executa a Calculadora do Windows */
            Runtime r = Runtime.getRuntime();
            
            String user = System.getProperty("user.name");
            r.exec("cmd /c start C:\\Users\\" + user + "\\Desktop\\Eludika\\Sources"
                    + "\\Jogo\\dist\\Joguinho.jar");
        }
        catch(java.io.IOException e) {
            
            //...
        }
    }
}  