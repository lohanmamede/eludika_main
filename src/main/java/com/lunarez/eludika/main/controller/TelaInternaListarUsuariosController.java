package com.lunarez.eludika.main.controller;

import com.lunarez.eludika.main.model.response.UsuarioResponseModel;
import com.lunarez.eludika.main.model.response.ews.UsuariosEWS;
import static java.lang.System.out;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * Classe que faz o controle da tela de usuarios
 *
 * @author eres
 */
public class TelaInternaListarUsuariosController extends ControllerEludika implements Initializable {
    
    // ------------------------------------------------------------------ Campos
    private static TelaInternaListarUsuariosController controller;
    
    @FXML
    VBox vBoxLista;
    
    @FXML
    GridPane gridPane;
    
    @FXML
    Button botaoMaisUsuarios;
    
    String usuarioSelecionado;
    
    int linhaBase = 0;
    
    String termoDePesquisa;
    
    
    // ------------------------------------------------------- Getters e Setters
    public static TelaInternaListarUsuariosController getController() {
        
        return TelaInternaListarUsuariosController.controller;
    }
    
    
    // ----------------------------------------------------------------- Métodos
    @Override
    @SuppressWarnings("static-access")
    public void initialize(URL url, ResourceBundle rb) {
        
        /* Armazenamento da instância deste controller para uso em outros contextos */
        this.controller = this;
        
        this.termoDePesquisa = TelaPrincipalController.getController().textFieldPesquisar.getText();

        if(super.servicoDesejado.equals("Pesquisa")) {
        
            this.pesquisarUsuario();
        }
        else if(super.servicoDesejado.equals("Amigos")) {
        
            this.listarAmigos();
        }
    } 
    
    @FXML
    public void pesquisarUsuario() {
        
        UsuariosEWS usuariosEWS = new UsuariosEWS();
        
        ArrayList<UsuarioResponseModel> urml = (ArrayList) usuariosEWS
                .obterUsuarios(this.termoDePesquisa, 0, 20);
        
        int linha = 0;
            
        int coluna = 0;
        
        int elemento = 0;
        
        Button[] buttons = new Button[20]; 
        
        for(UsuarioResponseModel usuario : urml) {
            
            final int linhaFinal = 0;
            
            final int colunaFinal = 0;
            
            HBox hBoxInterna = (HBox) super.carregarMolde("/fxml/MoldeUsuario.fxml");
                
            this.gridPane.add(hBoxInterna, coluna, linha); 

            Circle circleInterno = (Circle) hBoxInterna.getChildren().get(0);

            super.arredondarImagem("/images/perfil/avatars/" 
                    + usuario.getImagemPerfil() + ".png", circleInterno);

            GridPane gridPaneInterno = (GridPane) hBoxInterna.getChildren().get(1);

            buttons[elemento] = (Button) gridPaneInterno.getChildren().get(0);

            buttons[elemento].setText(usuario.getNomeCompleto());

            Label labelInterna = (Label) gridPaneInterno.getChildren().get(1);
            
            String tipo = (usuario.getTipo() == 'A' ? "Aluno" : "Professor");

            labelInterna.setText("@" + usuario.getCodinome() + " - " + tipo);

            buttons[elemento].setOnAction(acao -> 
                    this.botaoClicado(colunaFinal, linhaFinal, usuarioSelecionado)); 
            
            if(coluna == 1) linha++;
            
            coluna = (coluna == 0 ? 1 : 0);    
            
            elemento++;
        }
        
        if(elemento != 20) {
            
            this.botaoMaisUsuarios.setDisable(true);
        }
        
        /* Definição da linha onde começara o preenchimento na próxima chamada */
        this.linhaBase += 10;
    }   
    
    @FXML
    public void listarAmigos() {
     
    }
    
    private void botaoClicado(int coluna, int linha, String imagem) {
        
        out.println("Botão: " + coluna + " - " + linha + " pressionado! Imagem: " + imagem);
        this.usuarioSelecionado = imagem;
    }
}

//        Button[] buttons = new Button[20];  
//        
//        int elemento = 0;
//        
//        for (int j = 0 + this.linhaBase; j <= 9 + this.linhaBase; j++) {
//            
//            for (int i = 0; i <= 1; i++) {
//                
//                 if(elemento == buttons.length) {
//                    
//                      break;
//                 }
//                      
//                final int coluna = i;
//                
//                final int linha = j;
//                
//                // final String usuarioSelecionado = Integer.toString(botaoN + 1);
//                
//                HBox hBoxInterna = (HBox) super.carregarMolde("/fxml/TelaAmigos.fxml");
//                
//                this.gridPane.add(hBoxInterna, i, j); 
//                
//                Circle circleInterno = (Circle) hBoxInterna.getChildren().get(0);
//                
//                // super.arredondarImagem("imagem do usuario", circleInterno);
//        
//                GridPane gridPaneInterno = (GridPane) hBoxInterna.getChildren().get(1);
//
//                buttons[elemento] = (Button) gridPaneInterno.getChildren().get(0);
//                
//                // buttons[elemento].setText("Nome do usuário");
//                
//                Label labelInterna = (Label) gridPaneInterno.getChildren().get(1);
//                
//                // labelInterna.setText("Informações do usuário");
//                
//                buttons[elemento].setOnAction(acao -> 
//                        this.botaoClicado(coluna, linha, usuarioSelecionado));
//                
//                elemento++;
//            }
//        }    
//        
//        if(elemento != buttons.length) {
//            
//            
//        }
