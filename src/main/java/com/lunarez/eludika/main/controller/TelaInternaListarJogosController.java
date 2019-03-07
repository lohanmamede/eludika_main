package com.lunarez.eludika.main.controller;

import static com.lunarez.eludika.main.model.response.AutenticacaoResponseModel.getSessao;
import com.lunarez.eludika.main.model.response.JogoResponseModel;
import com.lunarez.eludika.main.model.response.UsuarioTemJogoResponseModel;
import com.lunarez.eludika.main.model.response.ews.JogosDoUsuarioEWS;
import com.lunarez.eludika.main.model.response.ews.JogosEWS;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Classe que faz o controle da tela de cadastro
 *
 * @author eres
 */
public class TelaInternaListarJogosController extends ControllerEludika implements Initializable {

    // ------------------------------------------------------------------ Campos
    @FXML
    Label labelTitulo;
    
    @FXML
    Button botaoMaisJogos;
    
    @FXML
    VBox vBoxLista;
    
    @FXML
    GridPane gridPane;
    
    String jogoSelecionado;
    
    int linhaBase = 0;
    
    String termoDePesquisa;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.termoDePesquisa = TelaPrincipalController.getController().textFieldPesquisar.getText();

        if(super.servicoDesejado.equals("Pesquisa")) {
        
            this.pesquisarJogo();
        }
        else if(super.servicoDesejado.equals("Meus Jogos")) {
        
            this.listarMeusJogos();
        }
    }
    
    @FXML
    public void pesquisarJogo() {
        
        JogosEWS jogosEWS = new JogosEWS();
        
        ArrayList<JogoResponseModel> jrml = (ArrayList) jogosEWS
                .obterJogos(this.termoDePesquisa, 0, 20);
        
        int linha = 0;
            
        int coluna = 0;
        
        int elemento = 0;
        
        Button[] buttons = new Button[20]; 
        
        for(JogoResponseModel jogo : jrml) {
            
            final int linhaFinal = 0;
            
            final int colunaFinal = 0;
            
            HBox hBoxInterna = (HBox) super.carregarMolde("/fxml/MoldeJogo.fxml");
                
            this.gridPane.add(hBoxInterna, coluna, linha); 

            Circle circleInterno = (Circle) hBoxInterna.getChildren().get(0);

            super.arredondarImagem(jogo.getLogotipo(), circleInterno);
            
            // super.arredondarImagem("/images/perfil/avatars/" 
            //        + jogo.getImagemPerfil() + ".png", circleInterno);

            GridPane gridPaneInterno = (GridPane) hBoxInterna.getChildren().get(1);

            buttons[elemento] = (Button) gridPaneInterno.getChildren().get(0);

            buttons[elemento].setText(jogo.getNome());

            Label labelInterna = (Label) gridPaneInterno.getChildren().get(1);

            labelInterna.setText("@" + jogo.getOferecidoPor() + " - Tamanho: " + jogo.getTamanho() + "MB");

            buttons[elemento].setOnAction(acao -> 
                    this.botaoClicado(colunaFinal, linhaFinal, jogo)); 
            
            if(coluna == 1) linha++;
            
            coluna = (coluna == 0 ? 1 : 0);    
            
            elemento++;
        }
        
        if(elemento != 20) {
            
            this.botaoMaisJogos.setDisable(true);
        }
        
        /* Definição da linha onde começara o preenchimento na próxima chamada */
        this.linhaBase += 10;
    }   
    
    @FXML
    public void listarMeusJogos() {
        
     
    }
    
    private void botaoClicado(int coluna, int linha, JogoResponseModel jogo) {
        
        FXMLLoader loader = super.chamarTelaInterna("/fxml/TelaInternaDetalhesJogo.fxml", 
                TelaPrincipalController.getController().stakePane,
                TelaPrincipalController.getController().scrollPaneConteudo,
                TelaPrincipalController.getController().anchorPaneSombra);  

        TelaInternaDetalhesJogoController controller = loader.getController();
        
        // Settagem de dados no controller
        controller.idExternoJogo = jogo.getIdExterno();
        super.arredondarImagem(jogo.getLogotipo(), controller.circleLogotipo);
        controller.labelTitulo.setText(jogo.getNome());
        controller.labelDescricao.setText(jogo.getDescricao());
        controller.labelTamanho.setText(jogo.getTamanho() + " MB");
        controller.labelClassificacao.setText(jogo.getClassificacao());
        controller.labelOferecidoPor.setText(jogo.getOferecidoPor());
        
        /* Checa se jogo pertence a coleção do usuário pra saber como proceder */
        try {
            
            JogosDoUsuarioEWS ews = new JogosDoUsuarioEWS();
            UsuarioTemJogoResponseModel obterJogoDoUsuario;
            obterJogoDoUsuario = ews.obterJogoDoUsuario(getSessao().getIdExterno(), jogo.getIdExterno());
            
            if(obterJogoDoUsuario.getIdExternoUsuario().equals(getSessao().getIdExterno()) 
                    && obterJogoDoUsuario.getIdExternoJogo().equals(jogo.getIdExterno())) {
                
                controller.botaoAdicionarOuRemover.setText("Remover");     
            }
            
        }
        catch(HttpStatusCodeException exception) {
            
            controller.botaoAdicionarOuRemover.setText("Adicionar");  
        }    
    }
}  

