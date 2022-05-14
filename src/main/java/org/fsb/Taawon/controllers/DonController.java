package org.fsb.Taawon.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.fsb.Taawon.App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.fsb.Taawon.models.Don;
import org.fsb.Taawon.utils.DbConnect;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;

public class DonController {

    private ObservableList<Don> DonData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Don, Don> actionsCol;

    @FXML
    private Button addBtn;

    @FXML
    private TableView<Don> donTable;

    @FXML
    private TableColumn<?, ?> categorieCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> libelleCol;

    @FXML
    private TableColumn<?, ?> modepaieCol;

    @FXML
    private TableColumn<?, ?> montantCol;

    @FXML
    private TableColumn<?, ?> numCol;

    @FXML
    private TableColumn<?, ?> numchequeCol;

    @FXML
    private TableColumn<?, ?> numcompteCol;

    @FXML
    private TableColumn<?, ?> quantiteCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private Button cancelBtn;

    @FXML
    private ChoiceBox<String> categorieInput;

    @FXML
    private TextField cinInput;

    @FXML
    private DatePicker dateInput;

    @FXML
    private TextField libelleInput;

    @FXML
    private ChoiceBox<String> modepaieInput;

    @FXML
    private TextField montantInput;

    @FXML
    private TextField numchequeInput;

    @FXML
    private TextField numcompteInput;

    @FXML
    private TextField quantiteInput;

    @FXML
    private Button submitBtn;

    @FXML
    private ChoiceBox<String> typeInput;

    @FXML
    private Button editBtn;

    int selectedDonId;

    @FXML
    void initialize() throws SQLException {

        this.initTable();

        assert actionsCol != null : "fx:id=\"actionsCol\" was not injected: check your FXML file 'dons.fxml'.";
        assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'dons.fxml'.";
        assert donTable != null : "fx:id=\"donTable\" was not injected: check your FXML file 'dons.fxml'.";
        assert categorieCol != null : "fx:id=\"categorieCol\" was not injected: check your FXML file 'dons.fxml'.";
        assert dateCol != null : "fx:id=\"dateCol\" was not injected: check your FXML file 'dons.fxml'.";
        assert libelleCol != null : "fx:id=\"libelleCol\" was not injected: check your FXML file 'dons.fxml'.";
        assert modepaieCol != null : "fx:id=\"modepaieCol\" was not injected: check your FXML file 'dons.fxml'.";
        assert montantCol != null : "fx:id=\"montantCol\" was not injected: check your FXML file 'dons.fxml'.";
        assert numCol != null : "fx:id=\"numCol\" was not injected: check your FXML file 'dons.fxml'.";
        assert numchequeCol != null : "fx:id=\"numchequeCol\" was not injected: check your FXML file 'dons.fxml'.";
        assert numcompteCol != null : "fx:id=\"numcompteCol\" was not injected: check your FXML file 'dons.fxml'.";
        assert quantiteCol != null : "fx:id=\"quantiteCol\" was not injected: check your FXML file 'dons.fxml'.";
        assert typeCol != null : "fx:id=\"typeCol\" was not injected: check your FXML file 'dons.fxml'.";

        if (this.typeInput != null) {
            this.typeInput.getItems().add("Financier");
            this.typeInput.getItems().add("Materiel");

            this.typeInput.setOnAction(event -> {
                if (this.typeInput.getValue().equals("Financier")) {
                    this.numcompteInput.setDisable(false);
                    this.numchequeInput.setDisable(false);
                    this.modepaieInput.setDisable(false);
                } else {
                    this.numcompteInput.setDisable(true);
                    this.numchequeInput.setDisable(true);
                    this.modepaieInput.setDisable(true);
                }
            });

        }

        if (this.categorieInput != null) {
            this.categorieInput.getItems().add("Vêtements");
            this.categorieInput.getItems().add("Don Alimentaire");
            this.categorieInput.getItems().add("Livres");
            this.categorieInput.getItems().add("Médicaments");
            this.categorieInput.getItems().add("Autres");
        }

        if (this.modepaieInput != null) {
            this.modepaieInput.getItems().add("Espece");
            this.modepaieInput.getItems().add("Cheque");
            this.modepaieInput.getItems().add("Virement");

            this.modepaieInput.setOnAction(event -> {
                if (this.modepaieInput.getValue() == "Cheque") {
                    this.numchequeInput.setDisable(false);
                    this.numcompteInput.setDisable(true);
                    this.numcompteInput.clear();
                } else if (this.modepaieInput.getValue() == "Virement") {
                    this.numchequeInput.setDisable(true);
                    this.numcompteInput.setDisable(false);
                    this.numchequeInput.clear();
                } else {
                    this.numchequeInput.setDisable(true);
                    this.numcompteInput.setDisable(true);
                    this.numchequeInput.clear();
                    this.numcompteInput.clear();
                }
            });
        }

        if (this.quantiteInput != null) {
            this.quantiteInput.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    this.quantiteInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }

        if (this.montantInput != null) {
            this.montantInput.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    this.montantInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }

        if (this.numchequeInput != null) {
            this.numchequeInput.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    this.numchequeInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }

        if (this.numcompteInput != null) {
            this.numcompteInput.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    this.numcompteInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }

        if (this.cinInput != null) {
            this.cinInput.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    this.cinInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }

        if (this.submitBtn != null) {
            this.submitBtn.setOnAction(event -> {
                if (this.cinInput.getText().isEmpty() ||
                        this.libelleInput.getText().isEmpty() ||
                        this.categorieInput.getValue() == null ||
                        this.typeInput.getValue() == null ||
                        this.dateInput.getValue() == null

                ) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Veuillez remplir tous les champs");
                    alert.showAndWait();
                } else {
                    try {

                        Connection c = DbConnect.getInstance().getConnection();

                        String sql = "INSERT INTO dons (libelle, categorie, type, date_don, quantite, montant, mode_paiement, num_compte, num_cheque, cin) VALUES (?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement stmt = c.prepareStatement(sql);

                        stmt.setString(1, this.libelleInput.getText());
                        stmt.setString(2, this.categorieInput.getValue());
                        stmt.setString(3, this.typeInput.getValue());
                        // stmt.setDate(4, Date.valueOf(this.dateInput.getValue()));

                        stmt.setDate(4, Date.valueOf(LocalDate.of(this.dateInput.getValue().getYear(),
                                this.dateInput.getValue().getMonthValue(), this.dateInput.getValue().getDayOfMonth())));

                        stmt.setInt(5, Integer.parseInt(this.quantiteInput.getText()));
                        stmt.setDouble(6, Double.parseDouble(this.montantInput.getText()));

                        if (this.modepaieInput.getValue() == "") {
                            stmt.setString(7, null);
                        } else {
                            stmt.setString(7, this.modepaieInput.getValue());
                        }

                        if (this.modepaieInput.getValue() == "Espece") {
                            stmt.setNull(8, java.sql.Types.INTEGER);
                            stmt.setNull(9, java.sql.Types.INTEGER);
                        } else if (this.modepaieInput.getValue() == "Cheque") {
                            stmt.setNull(8, java.sql.Types.INTEGER);
                            stmt.setInt(9, Integer.parseInt(this.numchequeInput.getText()));
                        } else if (this.modepaieInput.getValue() == "Virement") {
                            stmt.setInt(8, Integer.parseInt(this.numcompteInput.getText()));
                            stmt.setNull(9, java.sql.Types.INTEGER);
                        } else {
                            stmt.setNull(8, java.sql.Types.INTEGER);
                            stmt.setNull(9, java.sql.Types.INTEGER);
                        }

                        stmt.setInt(10, Integer.parseInt(this.cinInput.getText()));

                        stmt.executeUpdate();

                        // Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        // alert.setTitle("Succès");
                        // alert.setHeaderText("Don ajouté avec succès");
                        // alert.initOwner(this.cinInput.getScene().getWindow());
                        // alert.showAndWait();

                        c.close();

                        initTable();

                        // close
                        Stage stage = (Stage) this.submitBtn.getScene().getWindow();
                        stage.close();

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            });
        }

        if (cancelBtn != null) {
            cancelBtn.setOnAction(event -> {
                // close
                Stage stage = (Stage) this.cancelBtn.getScene().getWindow();
                stage.close();

            });
        }

        if (editBtn != null) {
            editBtn.setOnAction(event -> {
                if (this.cinInput.getText().isEmpty() ||
                        this.libelleInput.getText().isEmpty() ||
                        this.categorieInput.getValue() == null ||
                        this.typeInput.getValue() == null ||
                        this.dateInput.getValue() == null

                ) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Veuillez remplir tous les champs");
                    alert.showAndWait();
                } else {
                    try {

                        Connection c = DbConnect.getInstance().getConnection();

                        String sql = "UPDATE dons SET libelle = ?, categorie = ?, type = ?, date_don = ?, quantite = ?, montant = ?, mode_paiement = ?, num_compte = ?, num_cheque = ?, cin = ? WHERE num = ?";
                        PreparedStatement stmt = c.prepareStatement(sql);

                        stmt.setString(1, this.libelleInput.getText());
                        stmt.setString(2, this.categorieInput.getValue());
                        stmt.setString(3, this.typeInput.getValue());
                        // stmt.setDate(4, Date.valueOf(this.dateInput.getValue()));
                        stmt.setDate(4, Date.valueOf(LocalDate.of(this.dateInput.getValue().getYear(),
                                this.dateInput.getValue().getMonthValue(), this.dateInput.getValue().getDayOfMonth())));
                        stmt.setInt(5, Integer.parseInt(this.quantiteInput.getText()));
                        stmt.setDouble(6, Double.parseDouble(this.montantInput.getText()));

                        if (this.modepaieInput.getValue() == "") {
                            stmt.setString(7, null);
                        } else {
                            stmt.setString(7, this.modepaieInput.getValue());
                        }

                        if (this.modepaieInput.getValue() == "Espece") {
                            stmt.setNull(8, java.sql.Types.INTEGER);
                            stmt.setNull(9, java.sql.Types.INTEGER);
                        } else if (this.modepaieInput.getValue() == "Cheque") {
                            stmt.setNull(8, java.sql.Types.INTEGER);
                            stmt.setInt(9, Integer.parseInt(this.numchequeInput.getText()));
                        } else if (this.modepaieInput.getValue() == "Virement") {
                            stmt.setInt(8, Integer.parseInt(this.numcompteInput.getText()));
                            stmt.setNull(9, java.sql.Types.INTEGER);
                        } else {
                            stmt.setNull(8, java.sql.Types.INTEGER);
                            stmt.setNull(9, java.sql.Types.INTEGER);
                        }

                        stmt.setInt(10, Integer.parseInt(this.cinInput.getText()));
                        stmt.setInt(11, this.selectedDonId);

                        stmt.executeUpdate();

                        // Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        // alert.setTitle("Succès");
                        // alert.setHeaderText("Don modifié avec succès");
                        // alert.initOwner(this.cinInput.getScene().getWindow());
                        // alert.showAndWait();

                        c.close();

                        initTable();

                        // close
                        Stage stage = (Stage) this.editBtn.getScene().getWindow();
                        stage.close();

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            });

        }

    }

    public void openAjoutDonModal() throws IOException {
        Scene scene = new Scene(loadFXML("ajoutDon"));
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Ajouter un don");
        stage.setResizable(false);
        Image img = new Image(App.class.getResourceAsStream("imgs/logo.png"));
        stage.getIcons().add(img);
        stage.show();

    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(this);
        return fxmlLoader.load();
    }

    void initTable() throws SQLException {

        this.donTable.getItems().clear();
        this.DonData.clear();

        if (this.donTable != null) {

            this.numCol.setCellValueFactory(new PropertyValueFactory<>("num"));
            this.libelleCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            this.categorieCol.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            this.typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            this.dateCol.setCellValueFactory(new PropertyValueFactory<>("date_don"));
            this.quantiteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            this.montantCol.setCellValueFactory(new PropertyValueFactory<>("montant"));
            this.modepaieCol.setCellValueFactory(new PropertyValueFactory<>("mode_paiement"));
            this.numcompteCol.setCellValueFactory(new PropertyValueFactory<>("num_compte"));
            this.numchequeCol.setCellValueFactory(new PropertyValueFactory<>("num_cheque"));

            this.actionsCol.setCellFactory(tc -> new TableCell<Don, Don>() {
                Button edit = new Button("");
                Button supp = new Button("");

                HBox buttons = new HBox(5, edit, supp);

                @Override
                protected void updateItem(Don don, boolean empty) {
                    edit.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
                    supp.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
                    // set button width
                    edit.setMaxWidth(12);
                    supp.setMaxWidth(12);
                    edit.setMinWidth(12);
                    supp.setMinWidth(12);

                    URL urledit = App.class.getResource("imgs/edit.png");
                    Image imgEdit;

                    URL urldel = App.class.getResource("imgs/trash.png");
                    Image imgDel;

                    try {
                        imgEdit = new Image(urledit.toURI().toString());
                        ImageView imgViewEdit = new ImageView(imgEdit);
                        imgViewEdit.setFitHeight(12);
                        imgViewEdit.setPreserveRatio(true);
                        edit.setGraphic(imgViewEdit);

                        imgDel = new Image(urldel.toURI().toString());
                        ImageView imgViewDel = new ImageView(imgDel);
                        imgViewDel.setFitHeight(12);
                        imgViewDel.setPreserveRatio(true);
                        supp.setGraphic(imgViewDel);

                    } catch (URISyntaxException e) {

                        e.printStackTrace();
                    }

                    // when edit is clicked
                    edit.setOnMouseClicked(ev -> {
                        try {
                            openEditModal(getTableRow().getItem());
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    });

                    // when del is clicked
                    supp.setOnMouseClicked(ev -> {

                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Supprimer Don");
                        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce Don?");
                        alert.initOwner(supp.getScene().getWindow());

                        // option != null.
                        Optional<ButtonType> option = alert.showAndWait();

                        if (option.get() == null) {

                        } else if (option.get() == ButtonType.OK) {
                            int donId = getTableRow().getItem().getNum();
                            try {
                                Connection connection = DbConnect.getInstance().getConnection();
                                PreparedStatement statement = connection
                                        .prepareStatement("DELETE FROM dons WHERE num = ?");
                                statement.setInt(1, donId);
                                statement.executeUpdate();
                                initTable();
                            } catch (SQLException e) {

                                e.printStackTrace();
                            }

                            try {
                                // deleteAdherent(userId);
                                initTable();
                            } catch (SQLException e) {

                                e.printStackTrace();
                            }
                        } else if (option.get() == ButtonType.CANCEL) {

                        } else {

                        }

                    });

                    buttons.setStyle("-fx-alignment: CENTER;");
                    buttons.setBackground(null);

                    super.updateItem(don, empty);
                    setGraphic(empty ? null : buttons);
                }

            });

            // get list of users
            Connection connection = DbConnect.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM dons ORDER BY num DESC";
            // System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Don don = new Don();
                don.setNum(rs.getInt("num"));
                don.setLibelle(rs.getString("libelle"));
                don.setCategorie(rs.getString("categorie"));
                don.setType(rs.getString("type"));
                don.setDate_don(rs.getObject("date_don", LocalDate.class));
                don.setQuantite(rs.getInt("quantite"));
                don.setMontant(rs.getDouble("montant"));
                don.setMode_paiement(rs.getString("mode_paiement"));
                don.setNum_compte(rs.getInt("num_compte"));
                don.setNum_cheque(rs.getInt("num_cheque"));
                don.setCin(rs.getInt("cin"));
                this.DonData.add(don);
            }

            statement.close();
            connection.close();

            this.donTable.getItems().addAll(DonData);

            // System.out.println(this.DonData);
        }

    }

    public void openEditModal(Don don) throws IOException {

        this.selectedDonId = don.getNum();

        Scene scene = new Scene(loadFXML("modifDon"));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Modifier Don");
        // Add icon to application
        Image img = new Image(App.class.getResourceAsStream("imgs/logo.png"));
        stage.getIcons().add(img);
        stage.setResizable(false);
        stage.show();

        // System.out.println(ad);

        this.libelleInput.setText(don.getLibelle());
        this.categorieInput.setValue(don.getCategorie());
        this.typeInput.setValue(don.getType());
        this.dateInput.setValue(don.getDate_don());
        this.quantiteInput.setText(String.valueOf(don.getQuantite()));
        this.montantInput.setText(String.valueOf(don.getMontant()));
        this.modepaieInput.setValue(don.getMode_paiement());
        this.numcompteInput.setText(String.valueOf(don.getNum_compte()));
        this.numchequeInput.setText(String.valueOf(don.getNum_cheque()));
        this.cinInput.setText(String.valueOf(don.getCin()));

    }

}
