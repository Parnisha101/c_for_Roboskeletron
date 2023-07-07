package com.calculator_for_roboskeletron;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    int num1 = 0;
    String snum1 = "";
    int num2 = 0;
    String snum2 = "";
    String action = null;

    @FXML
    private Button buttonPlus, buttonSubs, buttonDiv, buttonMult, buttonPerc, button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    @FXML
    private TextField textField;

    @FXML
    public void pressNum(ActionEvent event) {
        Button button = (Button) event.getSource();
        textField.setText(textField.getText() + button.getText());
        if (action == null)
            snum1 = snum1 + button.getText();
        else
            snum2 = snum2 + button.getText();
    }
    @FXML
    public void pressAct(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (action == null) {
            switch (button.getText()) {
                case ("+"):
                    action = "+";
                    break;
                case ("-"):
                    action = "-";
                    break;
                case ("*"):
                    action = "*";
                    break;
                case ("/"):
                    action = "/";
                    break;
                case ("%"):
                    textField.setText("soon_tm");
                    break;
            }
            textField.setText(textField.getText() + button.getText());
        } else {
            result();
            switch (button.getText()) {
                case ("+"):
                    action = "+";
                    break;
                case ("-"):
                    action = "-";
                    break;
                case ("*"):
                    action = "*";
                    break;
                case ("/"):
                    action = "/";
                    break;
                case ("%"):
                    textField.setText("soon_tm");
                    break;
            }
            textField.setText(textField.getText() + button.getText());
        }

    }
    @FXML
    public void wipe() {
        textField.setText(null);
        num1 = 0;
        snum1 = "";
        num2 = 0;
        snum2 = "";
        action = null;
    }
    @FXML
    public void delete() {
        String text = textField.getText();
        textField.setText(text.substring(0, text.length() - 1));
    }
    @FXML
    public void result() {
        num1 = Integer.parseInt(snum1);
        num2 = Integer.parseInt(snum2);
        switch (action) {
            case ("+"):
                num1 = num1 + num2;
                snum1 = String.valueOf(num1);
                textField.setText(snum1);
                break;
            case ("-"):
                num1 = num1 - num2;
                snum1 = String.valueOf(num1);
                textField.setText(snum1);
                break;
            case ("*"):
                num1 = num1 * num2;
                snum1 = String.valueOf(num1);
                textField.setText(snum1);
                break;
            case ("/"):
                num1 = num1 / num2;
                snum1 = String.valueOf(num1);
                textField.setText(snum1);
                break;
        }
        snum2 = "";
    }
}