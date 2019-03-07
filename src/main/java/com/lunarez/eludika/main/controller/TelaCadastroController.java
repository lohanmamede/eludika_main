package com.lunarez.eludika.main.controller;

import static com.lunarez.eludika.main.auxiliar.ValidadorEludika.*;
import com.lunarez.eludika.main.model.request.CriarUsuarioRequestModel;
import com.lunarez.eludika.main.model.response.ews.UsuariosEWS;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Classe que faz o controle da tela de cadastro
 *
 * @author eres
 */
public class TelaCadastroController extends ControllerEludika implements Initializable {

    // ------------------------------------------------------------------ Campos
    private static TelaCadastroController controller;
    
    @FXML
    AnchorPane anchorPane;
    @FXML
    ChoiceBox choiceBoxTipoUsuario;
    @FXML
    TextField textFieldNomeCompleto;
    @FXML
    TextField textFieldNomeDeUsuario;
    @FXML
    TextField textFieldEmail;
    @FXML
    PasswordField passwordFieldSenha;
    @FXML
    PasswordField passwordFieldRepetirSenha;
    @FXML
    DatePicker datePickerNascimento;
    @FXML
    Button buttonCancelarCadastro;
    @FXML
    Button buttonConcluirCadastro;
    @FXML
    Label labelTipoUsuario;
    @FXML
    Label labelNomeCompleto;
    @FXML
    Label labelNomeDeUsuario;
    @FXML
    Label labelEmail;
    @FXML
    Label labelSenha;
    @FXML
    Label labelSenhaNovamente;
    @FXML
    Label labelNascimento;
    
    
    public static TelaCadastroController getController() {
        
        return TelaCadastroController.controller;
    }
    

    // ----------------------------------------------------------------- Métodos
    /**
     * Método que configura a inicialização do controller da tela de cadastro
     *
     * @param url local do FXML da tela de cadastro
     * @param rb contém dados específicos de certos idiomas
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { // :D

        /* Armazena a instancia desse controller, para que ele possa ser usado 
        em contextos diferentes da tela a que ele pertence */
        TelaCadastroController.controller = this;

        /* Carregamento do choiceBox de seleção de tipos de usuário */
        choiceBoxTipoUsuario.setItems(
                observableArrayList("Tipo de Usuário", "Aluno", "Professor"));

        /* Definição do valor padrão do choiceBox */
        choiceBoxTipoUsuario.setValue("Tipo de Usuário");

        /* Chama todos os validadores baseados em eventos de foco */
        validacaoAoDesfocar();

        /* Define o 'range' de datas que serão selecionáveis no DataPicker
        responsável pela data de nascimento */
        limitarData(datePickerNascimento, LocalDate.of(1900, Month.JANUARY, 1),
                LocalDate.now());
    }
    

    // -------------------------------- Manipuladores de evento baseados em foco
    /**
     *
     */
    @FXML
    public void validacaoAoDesfocar() {

        /* Alerta caso a choiceBox de escolha do tipo de usuário esteja com
        a opção padrão selecionada ao desfocá-lo */
        validarChoiceBox(choiceBoxTipoUsuario, "Qual tipo de usuário você é?",
                labelTipoUsuario, "Tipo de Usuário");

        /* Alerta caso o campo responsável pelo nome completo esteja vazio ao
        desfocá-lo */
        checarCampoVazio(textFieldNomeCompleto,
                "O nome completo não pode ficar em branco!", labelNomeCompleto);

        /* Alerta caso o campo responsável pelo nome de usuário esteja vazio ao
        desfocá-lo */
        checarCampoVazio(textFieldNomeDeUsuario,
                "O nome de usuário não pode ficar em branco!", labelNomeDeUsuario);

        /* Alerta caso o campo responsável pelo email esteja vazio ao
        desfocá-lo e caso ele esteja fora do padrão de validação */
        validarAoDesfocar(textFieldEmail,
                "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$",
                "O e-mail não pode ficar em branco!",
                "Digite um e-mail válido!", labelEmail);

        /* Alerta caso os campos responsáveis pela senha estejam vazios ao
        desfocá-los e caso a senha esteja fora do padrão de validação */
        validarAoDesfocar(passwordFieldSenha,
                "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})",
                "Digite uma senha!",
                "A senha deve conter números, letras maiúsculas e minúsculas e"
                + " ter entre 8 e 20 dígitos!",
                labelSenha);

        /* Alerta caso os campos responsáveis pela senha não sejam 
        correspondentes */
        compararCampos(passwordFieldSenha, passwordFieldRepetirSenha,
                "Confirme a senha!", "A senha especificada precisa corresponder"
                + " com a senha digitada acima!", labelSenhaNovamente);

        /* Alerta caso o datePicker responsável pela data de nascimento esteja 
        vazio ao desfocá-lo */
        checarDatePickerVazio(datePickerNascimento,
                "A data de nascimento não pode ficar em branco!",
                labelNascimento);
    }
    

    // -------------------------------- Manipuladores de evento: ON KEY RELEASED
    /**
     * Método que faz a validação no campo correspondente ao nome completo a 
     * cada vez que uma tecla é digitada neste campo
     */
    @FXML
    public void validarNomeCompleto() {

        tamanhoMaximo(textFieldNomeCompleto, 50);
        validarAoDigitar(textFieldNomeCompleto, "[ A-Za-zÀ-ú]*");
        letrasIniciaisMaiusculas(textFieldNomeCompleto);
    }

    /**
     * Metodo que faz a validação no campo correspondente ao nome de usuário a 
     * cada vez que uma tecla é digitada neste campo
     */
    @FXML
    public void validarNomeDeUsuario() {

        tamanhoMaximo(textFieldNomeDeUsuario, 15);
        validarAoDigitar(textFieldNomeDeUsuario, "[A-Za-zÀ-ú]*");
    }

    /**
     * Método que apaga caracteres excedentes no campo correspondente ao email a
     * cada vez que uma tecla é digitada neste campo
     */
    @FXML
    public void definirTamanhoDoEmail() {

        tamanhoMaximo(textFieldEmail, 45);
    }
    

    // -------------------------------------- Manipuladores de evento: ON ACTION   
    /**
     * Método chamado ao clicar no botão de cancelar cadastro
     * 
     */
    @FXML
    public void handleButtonCancelarCadastro() {

        super.alternarParaTela("/fxml/TelaLogin.fxml", false, 600, 492);

        /* Transferência do foco a um Pane, evitando que elementos indesejados 
        da tela de login sejam focados, ao voltar a tela */
        TelaLoginController.getController().anchorPane.requestFocus();
    }

    /**
     * Método chamado ao clicar no botão de concluir cadastro
     * 
     */
    @FXML
    public void handleButtonConcluirCadastro() {

        /* Validação dos dados do formulário antes de prosseguir com o cadastro */
        if(checarValidacoes()) {
            
            try {
        
                /* Criação de um modelo para requisição de cadastro */
                CriarUsuarioRequestModel dados = new CriarUsuarioRequestModel();

                /* Preenchimento do modelo de requisição com as informações do formulário */
                dados.setTipo(choiceBoxTipoUsuario.getSelectionModel()
                        .getSelectedItem().toString().charAt(0));
                dados.setNomeCompleto(textFieldNomeCompleto.getText());
                dados.setCodinome(textFieldNomeDeUsuario.getText());
                dados.setEmail(textFieldEmail.getText());
                dados.setSenha(passwordFieldSenha.getText());
                dados.setDataNascimento(super.converterData(datePickerNascimento));
             

                /* Criação da variável que lida com as requisições relacionadas ao usuário */
                UsuariosEWS usuarioEWS = new UsuariosEWS();
            
                /* Requisição do serviço de cadastro de usuário ao webservice */
                String resposta = usuarioEWS.criarUsuario(dados);
                
                if(resposta != null && !resposta.isEmpty()) {
                    
                    super.mostrarAlerta(INFORMATION, "Eludika", 
                            "Cadastro realizado com sucesso", 
                            "Você já pode realizar o seu primeiro acesso");

                    super.alternarParaTela("/fxml/TelaLogin.fxml", false, 600, 492);

                    /* Evita o foco em elementos indesejados ao especificar o elemento a focar */
                    TelaLoginController.getController().anchorPane.requestFocus();
                }   
            }
            catch(HttpStatusCodeException excecaoDoWebService) {
 
                super.mostrarAlertaDoWebService(INFORMATION, "Eludika",
                    "Falha ao realizar cadastro", excecaoDoWebService.getResponseBodyAsByteArray());

                /* Evita o foco em elementos indesejados ao especificar o elemento a focar */
                this.anchorPane.requestFocus();
            }
            catch(Exception exceptionGenerica) {
            
                super.mostrarAlerta(INFORMATION, "Eludika",
                        "Falha ao realizar cadastro", "Tente novamente em alguns instantes");
            }
        }
    }

    
    // ---------------------------------------------------------- Demais Métodos
    /**
     * Metodo chamado pelo botão de conclusão de cadastro. Só permite que o 
     * cadastro prossiga se todos os campos estiverem sem mensagens de alerta
     *
     * @return se a validação for bem sucedida, retorna true
     */
    public boolean checarValidacoes() {

        /* A principio é validação é setada com false, e só se torna true
        se não houver erros no formulário */
        boolean validacaoOK = false;

        /* Como as validações que acionam as labels de alerta são baseadas em
        foco, é necessário requisitar o foco em todos os campos antes de fazer a
        validação final. Sem isso, caso o usuário clicar direto no botão de
        concluir cadastro ao abrir a tela, sem interagir com qualquer campo, 
        as labels de alerta ficaram vazias, como se não houvessem erros no 
        cadastro, mas nenhum campo estaria preenchido. Isso força os validadores
        baseados em foco, a emitirem o alerta de que os campos estão em branco.
        Após isso o foco é devolvido para o container hBox e a validação é feita */
        choiceBoxTipoUsuario.requestFocus();
        textFieldNomeCompleto.requestFocus();
        textFieldNomeDeUsuario.requestFocus();
        textFieldEmail.requestFocus();
        passwordFieldSenha.requestFocus();
        passwordFieldRepetirSenha.requestFocus();
        datePickerNascimento.requestFocus();
        anchorPane.requestFocus();

        /* O formulário é considerado validado se todas as labels de alerta 
        estiverem vazias. */
        if (labelTipoUsuario.getText().isEmpty()
                && labelNomeCompleto.getText().isEmpty()
                && labelNomeDeUsuario.getText().isEmpty()
                && labelEmail.getText().isEmpty()
                && labelSenha.getText().isEmpty()
                && labelSenhaNovamente.getText().isEmpty()
                && labelNascimento.getText().isEmpty()) {

            /* Formulário passou pela validação */
            validacaoOK = true;
        }

        /* Retorna o resultado da validação final */
        return validacaoOK;
    }
}