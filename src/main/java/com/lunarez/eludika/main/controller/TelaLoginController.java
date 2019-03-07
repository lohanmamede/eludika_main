package com.lunarez.eludika.main.controller;

import com.lunarez.eludika.main.model.response.ews.AutenticacaoEWS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.web.client.HttpStatusCodeException;
import javafx.scene.layout.AnchorPane;

/**
 * Classe que faz o controle da tela de login
 *
 * @author eres
 */
public class TelaLoginController extends ControllerEludika implements Initializable {
    
    // ------------------------------------------------------------------ Campos
    private static TelaLoginController controller;
    
    @FXML
    public AnchorPane anchorPane;
    
    @FXML
    TextField textFieldEmail;
    
    @FXML
    PasswordField passwordField;
    
    @FXML
    Button buttonEntrar;
    
    
    public static TelaLoginController getController() {
        
        return TelaLoginController.controller;
    }
    
    
    // ----------------------------------------------------------------- Métodos
    /**
     * Método que configura a inicialização do controller da tela de login
     *
     * @param url local do FXML da tela de login
     * @param rb contém dados específicos de certos idiomas
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { // :D

        /* Armazena a instancia desse controller, para ser usado em outros contextos */
        TelaLoginController.controller = this;

        /* Botão que será acionado caso o usuário tecle enter */
        buttonEntrar.setDefaultButton(true);
    }
    

    /** Método que lida com o botão de autenticação da tela de login */
    @FXML
    public void handleButtonEntrar() {

        try {
            
            /* Criação da variável que lida com as requisições relacionadas ao usuário */
            AutenticacaoEWS autenticacaoEWS = new AutenticacaoEWS();
            
            /* Requisição do serviço de login ao webservice */
            autenticacaoEWS.autenticar(textFieldEmail.getText(), passwordField.getText());
            
            super.alternarParaTela("/fxml/TelaPrincipal.fxml", true, 1000, 680);

            /* Evita o foco em elementos indesejados ao especificar o elemento a focar */
            TelaPrincipalController.getController().botaoPaginaInicial.requestFocus();
        }
        catch(HttpStatusCodeException excecaoDoWebService) {
 
            super.mostrarAlertaDoWebService(INFORMATION, "Eludika",
                    "Falha ao logar", excecaoDoWebService.getResponseBodyAsByteArray());

            /* Evita o foco em elementos indesejados ao especificar o elemento a focar */
            this.anchorPane.requestFocus();
        }
        catch(Exception exceptionGenerica) {
            
            super.mostrarAlerta(INFORMATION, "Eludika",
                    "Falha ao logar", "Tente novamente em alguns instantes");
        }
    }  
    
    
    /** Método que lida com o botão de novos cadastros da tela de login */
    @FXML
    public void handleButtonCriarConta() {
        
        super.alternarParaTela("/fxml/TelaCadastro.fxml", false, 650, 664);

        /* Evita o foco em elementos indesejados ao especificar o elemento a focar */
        TelaCadastroController.getController().anchorPane.requestFocus();
    }  
}

/*
Solução alternativa adotada para tirar foco do label de email

final BooleanProperty firstTime = new SimpleBooleanProperty(true);

...

public void removerFocoDoTextFieldPadrao() throws IOException {

    textFieldEmail.focusedProperty()
            .addListener((observable,  oldValue,  newValue) -> {

        if(newValue && firstTime.get()){

            anchorPane.requestFocus();
            firstTime.setValue(false);
        }
    });
}  
*/

