package com.lunarez.eludika.main.controller;

import static java.lang.System.out;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * Classe que faz o controle da tela de amigos
 *
 * @author eres
 */
public class TelaAvatarController extends ControllerEludika implements Initializable {
    
    // ------------------------------------------------------------------ Campos   
    private static TelaAvatarController controller;
    
    @FXML
    VBox vBoxConteudo;
    
    @FXML
    GridPane gridPane;
    
    @FXML
    private Button[] buttons = new Button[50];
    
    String imagemSelecionada;
    
    
    public static TelaAvatarController getController() {
        
        return TelaAvatarController.controller;
    }
    
    
    // ----------------------------------------------------------------- Métodos
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TelaAvatarController.controller = this;
        
        int elemento = 0;
        
        for (int j = 0; j <= 10; j++) {
            
            for (int i = 0; i <= 4; i++) {
                
                if(elemento == this.buttons.length) {
                    
                    break;
                }
                      
                final int coluna = i;
                
                final int linha = j;
                
                final String imagemSelecionada = Integer.toString(elemento + 1);
                
                Circle circle = new Circle(45);
                super.arredondarImagem("/images/perfil/avatars/" 
                    + imagemSelecionada + ".png", circle);
            
                this.buttons[elemento] = new Button("", circle);
                
                this.buttons[elemento].setStyle("-fx-background-radius: 5;");
                
                this.buttons[elemento].setOnAction(acao -> 
                        this.botaoClicado(coluna, linha, imagemSelecionada));
                
                this.gridPane.add(buttons[elemento], i, j);
                
                GridPane.setHalignment(buttons[elemento], HPos.CENTER);
                GridPane.setValignment(buttons[elemento], VPos.CENTER);
                GridPane.setMargin(buttons[elemento], new Insets(15, 0, 15, 0));
                
                elemento++;
            }
        }
    }
    
    private void botaoClicado(int coluna, int linha, String imagem) {
        
        out.println("Botão: " + coluna + " - " + linha + " pressionado! Imagem: " + imagem);
        this.imagemSelecionada = imagem;
    }
    
    @FXML
    public void cancelar() {
        
        TelaPrincipalController.getController().anchorPaneSombra.setVisible(false);
                
        TelaPrincipalController.getController().stakePane.getChildren().remove(2);
        
    }
    
    @FXML
    public void selecionar() {
        
        TelaPrincipalController.getController().anchorPaneSombra.setVisible(false);
                
        TelaPrincipalController.getController().stakePane.getChildren().remove(2);
        
        super.arredondarImagem("/images/perfil/avatars/" + this.imagemSelecionada + ".png",
                TelaEditarPerfilController.getController().circleImagemPerfil);
        
        TelaEditarPerfilController.getController().imagemPerfil = this.imagemSelecionada;
    }
    
    @FXML
    public void mostrarAvatares() {
        
        super.inserirConteudo("/fxml/TelaAvatar.fxml", vBoxConteudo, null, false);
    } 
}


/*

public class TelaAvatarController extends ControllerEludika implements Initializable {
    
    // ------------------------------------------------------------------ Campos
    @FXML
    VBox vBoxConteudo;
    
    @FXML
    GridPane gridPane;
    
    Circle[] circles;

    
    // ----------------------------------------------------------------- Métodos
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
//        circles = new Circle[50];
//        
//        this.gridPane.addRow(0, circles[0], circles[1], circles[2]);
        
//        int n = 1;
//        
//        int linha = 0;
//        
//        while(n <= 50) {
//
//            for(int coluna = 0; coluna <= 2; coluna++) {
//            
//                if(n <= 50) {
//                    
//                    super.arredondarImagem("/images/perfil/avatars/" 
//                            + Integer.toString(n) + ".png", circles[n]);
//                
//                    gridPane.add(circles[n], coluna, linha);
//                    
//                    if(n / 3 == 0) {
//                        
//                        gridPane.addRow(coluna, circles[]);
//                    }
//                
//                    n++;   
//                }
//            }
//            
//            linha++;   
//        }
//        
//        
//        for(int i = 1; i <= 50; i++) {
//           
//            
//            
//            
//            
            
            
        
        
//        super.inserirConteudo("/fxml/TelaAvatar.fxml", vBoxConteudo, 
//                TelaPrincipalController.getController().scrollPaneConteudo, true);
    } 
    
    @FXML
    public void cancelar() {
        
        super.chamarTelaInterna("/fxml/TelaEditarPerfil.fxml", 
                TelaPrincipalController.getController().stakePane,
                TelaPrincipalController.getController().scrollPaneConteudo, 
                TelaPrincipalController.getController().anchorPaneSombra);
    } 
    
    @FXML
    public void mostrarAvatares() {
        
        super.inserirConteudo("/fxml/TelaAvatar.fxml", vBoxConteudo, null, false);
    } 
}

*/
