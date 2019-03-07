package com.lunarez.eludika.main.controller;

import com.lunarez.eludika.main.model.request.SalvarJogoNaColecaoRequestModel;
import com.lunarez.eludika.main.model.response.AutenticacaoResponseModel;
import static com.lunarez.eludika.main.model.response.AutenticacaoResponseModel.getSessao;
import com.lunarez.eludika.main.model.response.ews.JogosDoUsuarioEWS;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;

/**
 * Classe que faz o controle da tela de turmas
 *
 * @author eres
 */
public class TelaInternaDetalhesJogoController extends ControllerEludika implements Initializable {
    
    // ------------------------------------------------------------------ Campos
    String idExternoJogo;
    
    @FXML
    Circle circleLogotipo;
    
    @FXML
    Label labelTitulo;
    
    @FXML
    Label labelDescricao;
    
    @FXML
    Label labelTamanho;
    
    @FXML
    Label labelClassificacao;
    
    @FXML
    Label labelOferecidoPor;
    
    @FXML
    Button botaoAdicionarOuRemover;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //...
    }       
    
    @FXML
    public void adicionarOuRemover() {
        
        JogosDoUsuarioEWS ews = new JogosDoUsuarioEWS();
        
        if(botaoAdicionarOuRemover.getText().equals("Remover")) {
            
            Alert alerta = new Alert(CONFIRMATION);
            alerta.setTitle("Eludika");
            alerta.setHeaderText("Remover jogo da coleção?");
            alerta.setContentText("Os dados de jogo serão perdidos");

            alerta.initModality(Modality.WINDOW_MODAL);

            Optional<ButtonType> resultado = alerta.showAndWait();

            if (resultado.get() == ButtonType.OK) {

                ews.deletarJogoDoUsuario(getSessao().getIdExterno(), this.idExternoJogo);
            
                super.mostrarAlerta(Alert.AlertType.INFORMATION, "Eludika", "Sucesso!", 
                        "Jogo retirado da coleção");

                botaoAdicionarOuRemover.setText("Adicionar");
            }     
        }
        else if(botaoAdicionarOuRemover.getText().equals("Adicionar")) {
            
            Alert alerta = new Alert(CONFIRMATION);
            alerta.setTitle("Eludika");
            alerta.setHeaderText("Adicionar jogo na coleção?");
            alerta.setContentText("Os dados de jogo serão sincronizados");

            alerta.initModality(Modality.WINDOW_MODAL);

            Optional<ButtonType> resultado = alerta.showAndWait();

            if (resultado.get() == ButtonType.OK) {

                SalvarJogoNaColecaoRequestModel rm = new SalvarJogoNaColecaoRequestModel();
                rm.setIdExternoUsuario(getSessao().getIdExterno());
                rm.setIdExternoJogo(this.idExternoJogo);
                ews.salvarJogoNaColecao(rm);

                super.mostrarAlerta(Alert.AlertType.INFORMATION, "Eludika", "Sucesso!", 
                        "Jogo adicionado na coleção");

                botaoAdicionarOuRemover.setText("Remover");;
            }
            
        } 
    }
}
