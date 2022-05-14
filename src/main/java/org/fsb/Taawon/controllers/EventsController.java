package org.fsb.Taawon.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fsb.Taawon.App;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class EventsController {

    private ObservableList<?> eventData = FXCollections.observableArrayList();
    @FXML
    private TableView<?> eventsTable;
    @FXML
    private ImageView add;

    @FXML
    private MenuItem date_d;

    @FXML
    private MenuItem date_f;

    @FXML
    private MenuItem titre;

    @FXML
    private MenuItem num;

    @FXML
    private ImageView delete1;

    @FXML
    private TableColumn<?, ?> but_tab;

    @FXML
    private MenuItem mot;

    @FXML
    private MenuItem objectif;

    @FXML
    private ImageView modif1;

    @FXML
    private TableColumn<?, ?> end;

    @FXML
    private TableColumn<?, ?> num_tab;

    @FXML
    private TableColumn<?, ?> titre_tab;

    @FXML
    private TableColumn<?, ?> begin;
    @FXML
    private TableColumn<?, ?> actionCol;
    @FXML
    private TextField but;
    @FXML
    private TextField adherents;

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

    private VBox content;
    @FXML
    private TableColumn<?, ?> c;
    @FXML
    private TableColumn<Object, Object> p;
    @FXML
    private TableColumn<Object, Object> numr;
    @FXML
    private TableColumn<Object, Object> numd;
    @FXML
    private TableColumn<Object, Object> t;
    @FXML
    private TableColumn<Object, Object> l;
    @FXML
    private TableColumn<Object, Object> adherent;
    private TextField depense;


    @FXML
    void ajoutEvent(MouseEvent event) throws IOException {
        Parent p = FXMLLoader.load(App.class.getResource("add_event.fxml"));
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene c=new Scene(p);
        s.setScene(c);
        s.show();


    }

    @FXML
    void rechercher_par_num(ActionEvent event) throws IOException {

    }

    @FXML
    void rechercher_par_titre(ActionEvent event) {

    }

    @FXML
    void rechercher_par_objectif(ActionEvent event) {

    }

    @FXML
    void rechercher_par_date_debut(ActionEvent event) {

    }

    @FXML
    void rechercher_par_date_fin(ActionEvent event) {

    }

    @FXML
    void rechercher_par_lieu(ActionEvent event) {

    }

    @FXML
    void rechercher_par_mot(ActionEvent event) {

    }





}
