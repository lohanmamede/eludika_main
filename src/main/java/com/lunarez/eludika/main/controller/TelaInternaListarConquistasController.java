package com.lunarez.eludika.main.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Classe que faz o controle da tela de turmas
 *
 * @author eres
 */
public class TelaInternaListarConquistasController extends ControllerEludika implements Initializable {
    
    // ------------------------------------------------------------------ Campos
    @FXML
    Label labelTitulo;
    
    @FXML
    Button botaoMaisConquistas;
    
    @FXML
    VBox vBoxLista;
    
    @FXML
    GridPane gridPane;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //...
    }    
    
}
