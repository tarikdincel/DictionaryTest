package com.example.dictionarytest;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private TextField srcBar;

    @FXML
    public TextArea engWord;
    @FXML
    private Label wordSrced;


    @FXML
    void srcBarAction(ActionEvent event) {
        Stage stage = (Stage) srcBar.getScene().getWindow();
        String srcWord = srcBar.getText();
        wordSrced.setText(srcWord);



    }



}
