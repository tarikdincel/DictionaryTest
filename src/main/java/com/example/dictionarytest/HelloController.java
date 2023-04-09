package com.example.dictionarytest;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class HelloController  {

    dictionaryEng engDictionary = new dictionaryEng();
    dictionaryFra fraDictionary = new dictionaryFra();
    dictionaryTur turDictionary = new dictionaryTur();
    dictionaryEll ellDictionary = new dictionaryEll();
    dictionarySwe sweDictionary = new dictionarySwe();
    dictionaryDeu deuDictionary = new dictionaryDeu();
    dictionaryIta itaDictionary = new dictionaryIta();


    @FXML
    private TextArea orgEnglish;

    @FXML
    private TextArea orgFrench;

    @FXML
    private TextArea orgGerman;

    @FXML
    private TextArea orgItalian;

    @FXML
    private TextArea orgModernGreek;

    @FXML
    private TextArea orgSwedish;

    @FXML
    private TextArea orgTurkish;


    @FXML
    private TextField srcBar;

    @FXML
    public TextArea engWord;
    @FXML
    private Label wordSrced;
    @FXML
    private Button searchWord;
    @FXML
    private Button editDictionary;
    @FXML
    private Button help;
    @FXML
    private BorderPane mainPane;




    @FXML
    public void srcBarAction(ActionEvent event) {
        Stage stage1 = (Stage) srcBar.getScene().getWindow();
        String srcWord = srcBar.getText();
        char firstLetter = srcWord.charAt(0);
        int alphabet = (char) ((int) firstLetter);

        if (97 <= alphabet && alphabet <= 122) {

            orgTurkish.setText(turDictionary.turkish(srcWord));
            orgEnglish.setText(engDictionary.english(srcWord));
            orgFrench.setText(fraDictionary.french(srcWord));
            orgGerman.setText(deuDictionary.deutsch(srcWord));
            orgItalian.setText(itaDictionary.italian(srcWord));
            orgSwedish.setText(sweDictionary.swedish(srcWord));
        }
        else {
            orgSwedish.setText(sweDictionary.swedish(srcWord));
            orgTurkish.setText(turDictionary.turkish(srcWord));
            orgModernGreek.setText(ellDictionary.greek(srcWord));
        }




    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void searchWordButton(ActionEvent e) throws IOException {

        Pane view=new FXMLLoader(HelloApplication.class.getResource("SearchWordScene.fxml")).load();
        mainPane.setCenter(view);
    }

    public void editDictionaryButton(ActionEvent e) throws IOException {

        Pane view= new FXMLLoader(HelloApplication.class.getResource("EditDictionaryScene.fxml")).load();
        mainPane.setCenter(view);
    }

    public void helpButton(ActionEvent e) throws IOException {
        Pane view= new FXMLLoader(HelloApplication.class.getResource("HelpScene.fxml")).load();
        mainPane.setCenter(view);
    }



}
