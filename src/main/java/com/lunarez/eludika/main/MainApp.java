package com.lunarez.eludika.main; // :D

import com.lunarez.eludika.main.controller.ControllerEludika;
import com.lunarez.eludika.main.controller.TelaLoginController;
import com.lunarez.eludika.main.model.request.SalvarJogoNaColecaoRequestModel;
import com.lunarez.eludika.main.model.response.UsuarioTemJogoResponseModel;
import com.lunarez.eludika.main.model.response.ews.JogosDoUsuarioEWS;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Este é o ponto de partida de toda a aplicação
 *
 * @author eres
 */
public class MainApp extends Application {

    // ------------------------------------------------------------------ Campos
    /**
     * Define o "palco" da aplicação, é um atributo estático pois o palco criado
     * na instanciação dessa classe é usado durante toda a execução da aplicação
     * e é usado pelo método também estático de gerenciamento de tela, que é
     * usada globalmente
     */
    private static Stage baseStage;

    public static Stage getBaseStage() {

        return baseStage;
    }

    // ----------------------------------------------------------------- Métodos
    /**
     * Método que inicia a aplicação
     *
     * @param stage "palco" onde as "cenas" (telas) serão exibidas
     * @throws java.lang.Exception
     */
    @Override
    public void start(final Stage stage) throws Exception { 
        
        /* Armazena o stage para ser manipulado por toda a aplicação */
        baseStage = stage;

        /* Definição do ícone da aplicação */
        baseStage.getIcons().add(new Image(getClass()
                .getResourceAsStream("/images/jogos.png")));

        /* Definição do título da janela */
        baseStage.setTitle("Eludika");

        /* Definição do FXML onde está estruturada a tela de login */
        Region region = FXMLLoader.load(getClass()
                .getResource("/fxml/TelaLogin.fxml"));

        /* Define e exibe a primeira tela da aplicação (Tela de login) */
        MainApp.alternarEntreTelas(region, false, 600, 492);

        /* Método necessário para retirar o foco do label de email na tela de
        login. Isso só pode ser feito após a inicialização completa da tela,
        logo, não pode ser feito no método de inicialização do respectivo 
        controller, deve ser feito após a tela ser completa iniciada */
        ControllerEludika controllerEludika = new ControllerEludika();
        
        TelaLoginController.getController().anchorPane.requestFocus();
    }

    /**
     * Método estático responsável por gerenciar todas as telas da aplicação
     *
     * @param region indica a tela que será exibida
     * @param redimensionavel indica se a tela deve ou não ser redimensionável
     * @param largura a largura do stage
     * @param altura a altura do stage
     * @throws java.io.IOException
     */
    public static void alternarEntreTelas(Region region, boolean redimensionavel, 
            double largura, double altura)
            throws IOException {
        
        /* Ocultamento do stage, para realização de configurações */
        baseStage.close();
        
        MainApp.getBaseStage().setMaximized(false);
        
        /* Definição da altura e da largura da tela */
        baseStage.setWidth(largura);
        baseStage.setHeight(altura);
        
        /* Centraliza a tela */
        baseStage.centerOnScreen();
        
        /* Define se a tela será redimensionável */
        baseStage.setResizable(redimensionavel);
        
        /* Redefinição da altura e da largura da tela para evitar bugs de
        redimensionamento */
        baseStage.setWidth(largura);
        baseStage.setHeight(altura);
        
        /* Cria uma "cena" que será exibida no "palco" a partir da tela que foi
        passada como parâmetro */
        Scene scene = new Scene(region);
        
        scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Nunito");

        /* Atribui a "cena" ao "palco" */
        baseStage.setScene(scene);
        
        /* Exibe a tela */
        baseStage.show(); 
    }

    public static void main(String[] args) {

        launch(args);
    }
}
