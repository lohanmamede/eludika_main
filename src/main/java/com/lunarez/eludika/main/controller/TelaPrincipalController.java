package com.lunarez.eludika.main.controller;

import com.lunarez.eludika.main.model.Usuario;
import com.lunarez.eludika.main.model.response.AutenticacaoResponseModel;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;

/**
 * Classe que faz o controle da tela principal
 *
 * @author eres
 */
public class TelaPrincipalController extends ControllerEludika implements Initializable {
    
    // ------------------------------------------------------------------ Campos
    private static TelaPrincipalController controller;
    
    Usuario usuarioLogado;
    
    @FXML
    StackPane stakePane;
    
    @FXML
    AnchorPane anchorPaneSombra;

    @FXML
    ScrollPane scrollPaneConteudo;
    
    @FXML
    Button botaoPaginaInicial;
    
    @FXML
    TextField textFieldPesquisar;
    
    @FXML
    Circle circleImagemPerfil;
    
    @FXML
    Label textFieldNomeDeExibicao;
    
    @FXML
    ChoiceBox choiceBoxtipoDePesquisa;
    
    @FXML
    Button botaoBuscar;
    
    @FXML
    String servicoDesejado;
    
    // ------------------------------------------------------- Getters e Setters
    public static TelaPrincipalController getController() {
        
        return TelaPrincipalController.controller;
    }
    
    
    // ------------------------------------------------- Método de inicialização
    @Override
    @SuppressWarnings("static-access")
    public void initialize(URL url, ResourceBundle rb) {
        
        /* Armazenamento da instância deste controller para uso em outros contextos */
        this.controller = this;
        
        /* Carregamento do choiceBox de seleção de tipos de usuário */
        choiceBoxtipoDePesquisa.setItems(
                observableArrayList("Jogo", "Turma"));

        /* Definição do valor padrão do choiceBox */
        choiceBoxtipoDePesquisa.setValue("Jogo");
        
        /* Preenchimento das informações da sessão iniciada */
        super.arredondarImagem("/images/perfil/avatars/" 
                + AutenticacaoResponseModel.getSessao().getImagemPerfil() + ".png", this.circleImagemPerfil);
        
        this.textFieldNomeDeExibicao.setText("@" + AutenticacaoResponseModel.getSessao().getCodinome());
       
        this.chamarTelaInternaInicial(); 
    }   
    
   
    // ---------------------------------------------------------- Demais métodos
    @FXML
    public void chamarTelaInternaInicial() {
        
        super.chamarTelaInterna("/fxml/TelaPaginaInicial.fxml", this.stakePane, 
                this.scrollPaneConteudo, this.anchorPaneSombra);
    }
      
    @FXML
    public void chamarTelaInternaPerfil() {
        
        super.chamarTelaInterna("/fxml/TelaPerfil.fxml", this.stakePane,
                this.scrollPaneConteudo, this.anchorPaneSombra);
    }
    
    @FXML
    @SuppressWarnings("static-access")
    public void chamarTelaInternaJogos() {
        
        super.servicoDesejado = new String("Meus Jogos");
        
        super.chamarTelaInterna("/fxml/TelaInternaListarJogos.fxml", this.stakePane, 
                this.scrollPaneConteudo, this.anchorPaneSombra);
    }
    
    @FXML
    @SuppressWarnings("static-access")
    public void chamarTelaInternaAmigos() {
        
        super.servicoDesejado = new String("Amigos");
        
        super.chamarTelaInterna("/fxml/TelaInternaListarUsuarios.fxml", this.stakePane, 
                this.scrollPaneConteudo, this.anchorPaneSombra);
    }
    
    @FXML
    public void chamarTelaInternaTurmas() {
        
        super.chamarTelaInterna("/fxml/TelaInternaListarTurmas.fxml", this.stakePane, 
                this.scrollPaneConteudo, this.anchorPaneSombra);
    }
    
    @FXML
    public void chamarTelaInternaConquistas() {
        
        super.chamarTelaInterna("/fxml/TelaInternaListarConquistas.fxml", this.stakePane, 
                this.scrollPaneConteudo, this.anchorPaneSombra);
    }

    
    @FXML
    @SuppressWarnings("static-access")
    public void pesquisar() {
        
        try {
            
            super.servicoDesejado = new String("Pesquisa");
        
            if(choiceBoxtipoDePesquisa.getSelectionModel().getSelectedItem()
                    .toString().equals("Usuário")) {

                super.chamarTelaInterna("/fxml/TelaInternaListarUsuarios.fxml", this.stakePane,
                    this.scrollPaneConteudo, this.anchorPaneSombra);  
            }
            else if (choiceBoxtipoDePesquisa.getSelectionModel().getSelectedItem()
                    .toString().equals("Jogo"))  {

                super.chamarTelaInterna("/fxml/TelaInternaListarJogos.fxml", this.stakePane,
                    this.scrollPaneConteudo, this.anchorPaneSombra);
            }  
        }
        finally {
            
            this.textFieldPesquisar.setText("");
        }
         
    }
    

    @FXML
    public void deslogar() {
        
        Alert alerta = new Alert(CONFIRMATION);
        alerta.setTitle("Eludika");
        alerta.setHeaderText("Deslogar-se");
        alerta.setContentText("Você tem certeza que deseja sair?");
        
        alerta.initModality(Modality.WINDOW_MODAL);

        Optional<ButtonType> resultado = alerta.showAndWait();
        
        if (resultado.get() == ButtonType.OK) {
            
            super.alternarParaTela("/fxml/TelaLogin.fxml", false, 600, 492);
            
            /* Desvinculação das credenciais do usuário atual da sessão */
            AutenticacaoResponseModel.getSessao().setIdExterno(null);
            AutenticacaoResponseModel.getSessao().setToken(null);

            /* Retirada do foco dos campos ao voltar na tela */
            TelaLoginController.getController().anchorPane.requestFocus();
        } 
    }
}