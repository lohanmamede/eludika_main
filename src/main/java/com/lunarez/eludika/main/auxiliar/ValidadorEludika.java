package com.lunarez.eludika.main.auxiliar;

import java.time.LocalDate;
import java.time.Month;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class ValidadorEludika {

    /**
     * Método que limita a quantidade de caracteres de um campo de texto,
     * excluíndo o que exceder
     *
     * @param textField campo de texto a ser manipulado
     * @param tamanho tamanho desejado
     */
    public static void tamanhoMaximo(TextField textField, int tamanho) { // :D

        /* Enquanto o tamanho do texto no TextField for maior que o tamanho 
        desejado... */
        while (textField.getText().length() > tamanho) {

            /* ... é gerada uma substring a partir do texto do campo, que 
            engloba todo o texto e tira o último caractere caractere,
            responsável por exceder o tamanho desejado, a substring é então 
            atribuida novamente ao campo, e o processo é repetido até a
            eliminação de todos os caracteres excedentes */
            textField.setText(textField
                    .getText().substring(0, textField.getText().length() - 1));
        }

        /* Faz com que o cursor permaneça sempre ao fim do campo */
        forcarCursor(textField);
    }

    /**
     * Método ideal para campos onde é desejável que o texto possa ser validado
     * conforme o usuário digita os dados
     *
     * @param textField campo de texto a ser manipulado
     * @param expressao expressão regular com o padrão de validação
     */
    public static void validarAoDigitar(TextField textField, String expressao) {

        /* Enquanto o texto no TextField contiver dados e não estiver no padrão
        da expressão regular desejada... */
        while (!textField.getText().isEmpty()
                && !textField.getText().matches(expressao)) {

            char ultimoCaractere = textField.getText()
                    .charAt(textField.getText().length() - 1);

            textField.setText(textField.getText()
                    .replace(ultimoCaractere + "", ""));
        }

        /* Faz com que o cursor permaneça sempre ao fim do campo */
        forcarCursor(textField);
    }

    /**
     * Método que faz validação e checa se um campo está em branco ao focá-lo ou
     * desfocá-lo
     *
     * @param textField campo de texto a ser manipulado
     * @param expressao expressão regular com o padrão de validação
     * @param alerta1 mensagem se algum campo estiver vazio
     * @param alerta2 mensagem se campo está fora do padrão
     * @param alertaLabel label onde a mensagem de alerta é exibido
     */
    public static void validarAoDesfocar(TextField textField,
            String expressao, String alerta1, String alerta2,
            Label alertaLabel) {

        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean oldPropertyValue, Boolean newPropertyValue) {
                
                /* Validador de campo ao focar: a label de alerta ficará em branco */
                if (newPropertyValue) {

                    /* Ao focar no campo de texto, não mostrar textos de alerta. */
                    alertaLabel.setText("");
                } 
                /* Quando o campo de texto perder o foco, passará por validação, 
                e se necessário, serão mostradas labels de alerta */
                else {

                    if (textField.getText().isEmpty()) {

                        alertaLabel.setText(alerta1);
                    } else if (!textField.getText().matches(expressao)) {

                        alertaLabel.setText(alerta2);
                    } else {

                        alertaLabel.setText("");
                    }
                }
            }
        });
    }

    /**
     * Método que apenas checa se um campo está em branco ao focá-lo ou
     * desfocá-lo
     *
     * @param textField
     * @param alertaCampoVazio
     * @param label
     */
    public static void checarCampoVazio(TextField textField,
            String alertaCampoVazio, Label label) {

        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean oldPropertyValue, Boolean newPropertyValue) {

                /* Validador de campo ao focar: a label de alerta ficará em branco */
                if (newPropertyValue) {

                    /* Ao focar no campo de texto, não mostrar textos de alerta. */
                    label.setText("");
                } 
                /* Quando o campo de texto perder o foco, passará por validação, 
                e se necessário, serão mostradas labels de alerta */
                else {

                    String texto = textField.getText();

                    if (texto.isEmpty()) {

                        label.setText(alertaCampoVazio);
                    }
                }
            }
        });
    }

    /**
     * Método que checa se um DatePicker está em branco ao desfocá-lo
     *
     * @param datePicker
     * @param alertaDatePickerVazio
     * @param alertaLabel
     */
    public static void checarDatePickerVazio(DatePicker datePicker,
            String alertaDatePickerVazio, Label alertaLabel) {

        datePicker.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean oldPropertyValue, Boolean newPropertyValue) {

                /* Validador de campo ao focar: a label de alerta ficará em branco*/
                if (newPropertyValue) {

                    /* Ao focar no campo de texto, não mostrar textos de alerta.*/
                    alertaLabel.setText("");
                } 
                /* Quando o campo de texto perder o foco, passará por validação, 
                e se necessário, serão mostradas labels de alerta*/
                else {

                    if (datePicker.getValue() == null) {

                        alertaLabel.setText(alertaDatePickerVazio);
                    }
                }
            }
        });
    }

    /**
     * Método que compara se dois campos correspondem e gera alertas
     *
     * @param senha1 campo onde a senha é digitada
     * @param senha2 campo onde a senha é repetida
     * @param alerta1 mensagem se algum campo estiver vazio
     * @param alerta2 mensagem se as senhas não corresponderem
     * @param alertaLabel label onde a mensagem de alerta é exibido
     */
    public static void compararCampos(TextField senha1, TextField senha2,
            String alerta1, String alerta2, Label alertaLabel) {

        senha2.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean oldPropertyValue, Boolean newPropertyValue) {

                /* Validador de campo ao focar: a label de alerta ficará em branco */
                if (newPropertyValue) {

                    /* Ao focar no campo de texto, não mostrar textos de alerta */
                    alertaLabel.setText("");
                }
                /* Quando o campo de texto perder o foco, passará por validação, 
                e se necessário, serão mostradas labels de alerta  */
                else {

                    String texto = senha2.getText();

                    if (texto.isEmpty()) {

                        alertaLabel.setText(alerta1);
                    } else if (!senha1.getText().equals(senha2.getText())) {

                        alertaLabel.setText(alerta2);
                    }
                }
            }
        });

        /* Quando o campo senha for mudado, o senha novamente reage a isto */
        senha1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean oldPropertyValue, Boolean newPropertyValue) {

                /* Se a senha principal for desfocada... */
                if (!newPropertyValue) {

                    /* Se tanto a senha principal quanto a repetição estiverem em branco... */
                    if (senha1.getText().isEmpty() && senha2.getText().isEmpty()) {

                        /* Mostrar alerta que a repetição está em branco! */
                        alertaLabel.setText(alerta1);
                    } 
                    /* Se tanto a senha principal quanto a repetição conterem texto... */
                    else if (!senha1.getText().isEmpty() && !senha2.getText().isEmpty()) {

                        /* Se a senha principal for diferente da repetição... */
                        if (!senha1.getText().equals(senha2.getText())) {

                            /* Mostrar alerta de que a repetição não corresponde a senha principal! */
                            alertaLabel.setText(alerta2);
                        } 
                        /* Se a senha principal for igual a repetição... */
                        else if (senha1.getText().equals(senha2.getText())) {

                            /* Remover qualquer alerta da repetição! */
                            alertaLabel.setText("");
                        }
                    } 
                    /* Se a senha principal conter texto e a repetição não...
                    ou se a senha principal não conter texto e a repetição contiver... */
                    else if ((senha2.getText().isEmpty() && !senha1.getText().isEmpty())
                            || (!senha2.getText().isEmpty() && senha1.getText().isEmpty())) {

                        /* Mostrar alerta de que a repetição não corresponde a senha principal! */
                        alertaLabel.setText(alerta2);
                    }
                }
            }
        });
    }

    // 
    /**
     * Método que checa se o choiceBox ficou na opção Default ao desfocá-lo
     *
     * @param choiceBox choiceBox a ser manipulado
     * @param alerta mensagem caso opção default esteja selecionada
     * @param alertaLabel label onde a mensagem de alerta é exibido
     * @param opcaoDefault valor da opção default
     */
    public static void validarChoiceBox(ChoiceBox choiceBox, String alerta,
            Label alertaLabel, String opcaoDefault) {

        choiceBox.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean oldPropertyValue, Boolean newPropertyValue) {

                /* Ao focar no choiceBox, não mostrar textos de alerta. */
                if (newPropertyValue) {

                    alertaLabel.setText("");
                } 
                /* Quando o campo de texto perder o foco, passará por validação, 
                e se necessário, serão mostradas labels de alerta */
                else {

                    String texto = choiceBox.getSelectionModel().getSelectedItem().toString();

                    if (texto.equals(opcaoDefault)) {

                        alertaLabel.setText(alerta);
                    }
                }
            }
        });
    }

    /**
     * @param textField
     */
    public static void letrasIniciaisMaiusculas(TextField textField) {

        String texto = textField.getText();

        if (!texto.isEmpty()) {

            String ultimoCaractere = Character.toString(texto.charAt(texto.length() - 1));

            if (!ultimoCaractere.equals(" ")) {

                /* String[] palavras = texto.split("\\s"); */
                String[] palavras = texto.split(" ");
                StringBuilder builder = new StringBuilder();

                for (String palavra : palavras) {

                    builder.append(palavra.substring(0, 1).toUpperCase())
                            .append(palavra.substring(1).toLowerCase());
                    
                    builder.append(" ");
                }

                texto = builder.toString().substring(0, builder.toString().length() - 1);

                textField.setText(texto);

                forcarCursor(textField);
            }
        }
    }

    /**
     * @param datePickerFormulario
     * @param minDataDesejada
     * @param maxDataDesejada
     */
    public static void limitarData(DatePicker datePickerFormulario,
            LocalDate minDataDesejada, LocalDate maxDataDesejada) {

        /* DataPicker usado para definir a data mínima */
        DatePicker minData = new DatePicker();
        
        /* Data mínima permitida será: ... */
        minData.setValue(LocalDate.of(1990, Month.JANUARY, 1));

        /* DataPicker usado para definir a data máxima */
        DatePicker maxData = new DatePicker();
        
        /* Data máxima permitida será: ... */
        maxData.setValue(maxDataDesejada);

        final Callback<DatePicker, DateCell> celulasDoDia;

        celulasDoDia = (final DatePicker datePicker) -> new DateCell() {

            @Override
            public void updateItem(LocalDate item, boolean empty) {

                super.updateItem(item, empty);

                if (item.isAfter(maxData.getValue())) { /* Desabilita as datas após a especificada */

                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); /* Muda a cor das células */
                }
                if (item.isBefore(minData.getValue())) { /* Desabilita as datas após a especificada */

                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); /* Muda a cor das células */
                }
            }
        };

        /* Finalmente, nós atualizados a fábrica de células do DatePicker */
        datePickerFormulario.setDayCellFactory(celulasDoDia);
    }

    /**
     * @param textField
     */
    public static void forcarCursor(TextField textField) {

        if (!textField.getText().equals("")) {

            for (int i = 0; i < textField.getLength(); i++) {

                textField.forward();
            }
        }
    }
}
