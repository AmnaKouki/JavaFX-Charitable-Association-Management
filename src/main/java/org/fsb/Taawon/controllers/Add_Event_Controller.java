package org.fsb.Taawon.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import java.sql.Connection;

public class Add_Event_Controller {

    @FXML
    private TextField but;
    @FXML
    private TextField adherents;
    @FXML
    private DatePicker date_d;
    @FXML
    private DatePicker date_f;
    @FXML
    private TextField titre;
    @FXML
    private MenuItem sexe;

    @FXML
    private MenuItem salaire;

    @FXML
    private CheckBox internationale;
    @FXML
    private Button g;
    @FXML
    private Button recette;

    @FXML
    private MenuItem condition;

    @FXML
    private MenuItem sante;

    @FXML
    private MenuItem civil;

    @FXML
    private CheckBox nationale;

    @FXML
    private TextField tariff;

    @FXML
    private MenuButton id;

    @FXML
    private MenuItem age;

    @FXML
    private TextField lieu;
    @FXML
    private Button added;
    @FXML
    void handlebox1() {
    if(nationale.isSelected()){ internationale.setSelected(false);}
    }
    @FXML
    void handlebox2() {
        if(internationale.isSelected()){ nationale.setSelected(false);}
    }
    @FXML
    void retourner(ActionEvent event) {

    }

    @FXML
    void ajouter_event1(MouseEvent event) {


    }

    @FXML
    void recettes(ActionEvent event) {

    }

}




