package com.example.dictionarytest;


import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

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
    private Button editWord;
    @FXML
    private Button addWord;
    @FXML
    private Button help;
    @FXML
    private BorderPane mainPane;
    @FXML
    public ChoiceBox<String> addWordCB1= new ChoiceBox<>();

    @FXML
    public ChoiceBox<String> addWordCB2= new ChoiceBox<>();
    @FXML
    private TextField enterWord=new TextField();
    @FXML
    private TextArea enterMeaning;
    @FXML
    private Button Add;
    @FXML
    TextField editEnterWord;
    @FXML
    Button editSearch;
    @FXML
    ChoiceBox<String> EditCB1= new ChoiceBox<>();
    @FXML
    ChoiceBox<String> EditCB2=new ChoiceBox<>();
    @FXML
    TextArea editResults;
    @FXML
    Button EDIT;


    public static String[]language={"Turkish","English","French","Italian","Swedish","German","Modern Greek"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    addWordCB1.getItems().addAll(language);
    addWordCB2.getItems().addAll(language);
    EditCB1.getItems().addAll(language);
    EditCB2.getItems().addAll(language);
    }
    Editor editor= new Editor();

    @FXML
    public void AddButtonAction(ActionEvent actionEvent) throws IOException {
        String word=enterWord.getText();
        String lang1=addWordCB1.getValue().toLowerCase().substring(0,3);
        String lang2=addWordCB2.getValue().toLowerCase().substring(0,3);
        String meanings= enterMeaning.getText();
        if(lang1.equals(lang2)){
            System.out.println("no");

        }
        else{editor.addWord(word,meanings,lang1,lang2);
            System.out.println("Added");}

    }
    public void EditSearchButton(ActionEvent actionEvent){
        String word=editEnterWord.getText();
        String lang1=EditCB1.getValue().toLowerCase().substring(0,3);
        String lang2=EditCB2.getValue().toLowerCase().substring(0,3);
        StringBuilder st=editor.editWord(word,lang1,lang2);
        editResults.setText(st.toString());



    }
    public void EditEDITButton(ActionEvent actionEvent) throws IOException {
        String lang1=EditCB1.getValue().toLowerCase().substring(0,3);
        String lang2=EditCB2.getValue().toLowerCase().substring(0,3);
        String path=lang1+"-"+lang2+".dict";

        File add= new File(path);
        FileWriter fw= new FileWriter(add,true);
        PrintWriter pw= new PrintWriter(fw);
        String editedVer=editResults.getText();
        pw.println(editedVer);
        pw.close();

    }








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

    public void editWordButton(ActionEvent e) throws IOException {

        Pane view= new FXMLLoader(HelloApplication.class.getResource("EditDictionaryScene.fxml")).load();
        mainPane.setCenter(view);
    }
    public void addWordButton(ActionEvent e) throws IOException {
        Pane view= new FXMLLoader(HelloApplication.class.getResource("AddWord.fxml")).load();
        mainPane.setCenter(view);
    }

    public void helpButton(ActionEvent e) throws IOException {
        Pane view= new FXMLLoader(HelloApplication.class.getResource("HelpScene.fxml")).load();
        mainPane.setCenter(view);
    }




}
