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
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import org.fsb.Taawon.App;
import org.fsb.Taawon.models.Adherent;
import org.fsb.Taawon.utils.DbConnect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Tooltip;

public class AdherentController {

	private ObservableList<Adherent> AdherentData = FXCollections.observableArrayList();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField confirmPassInput;

	@FXML
	private DatePicker datenaissInput;

	@FXML
	private TextField domaineInput;

	@FXML
	private TextField emailInput;

	@FXML
	private TextField nomInput;

	@FXML
	private TextField passInput;

	@FXML
	private TextField prenomInput;

	@FXML
	private TextField profInput;

	@FXML
	private TextField cinInput;

	@FXML
	private ChoiceBox<String> roleInput;

	@FXML
	private ChoiceBox<String> sexeInput;

	@FXML
	private TextField telInput;

	@FXML
	private Button submitBtn;

	@FXML
	private TableView<Adherent> adherentsTable;

	@FXML
	private TableColumn<Adherent, ?> datenaissanceCol;

	@FXML
	private TableColumn<Adherent, ?> emailCol;

	@FXML
	private TableColumn<Adherent, ?> nomCol;

	@FXML
	private TableColumn<Adherent, ?> numCol;

	@FXML
	private TableColumn<Adherent, ?> prenomCol;

	@FXML
	private TableColumn<Adherent, ?> roleCol;

	@FXML
	private TableColumn<Adherent, Adherent> actionsCol;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button editBtn;

	int selectedAdherentId;

	@FXML
	void initialize() throws SQLException {

		// init table
		this.initTable();

		// init sexe select
		if (this.sexeInput != null) {
			this.sexeInput.getItems().add("Male");
			this.sexeInput.getItems().add("Female");
		}

		// init role select
		if (this.roleInput != null) {
			this.roleInput.getItems().add("Administrateur");
			this.roleInput.getItems().add("Vice-précedent");
			this.roleInput.getItems().add("Secrétaire");
			this.roleInput.getItems().add("Trésorier");
			this.roleInput.getItems().add("Collecteur des dons");
			this.roleInput.getItems().add("Adhérent");
		}

		// allow only numbers in Tel Textfield
		if (this.telInput != null) {
			telInput.textProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue.matches("\\d*"))
					return;
				telInput.setText(newValue.replaceAll("[^\\d]", ""));
			});
		}

		// allow only numbers in CIN Textfield
		if (this.cinInput != null) {
			cinInput.textProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue.matches("\\d*"))
					return;
				cinInput.setText(newValue.replaceAll("[^\\d]", ""));
			});
		}

		// when add new btn clicked
		if (this.submitBtn != null) {
			submitBtn.setOnMouseClicked(event -> {
				try {

					// form validation
					if (this.nomInput.getText().trim().equals("") || this.prenomInput.getText().trim().equals("")
							|| this.cinInput.getText().trim().equals("") || this.sexeInput.getValue() == null
							|| this.profInput.getText().trim().equals("") || this.telInput.getText().trim().equals("")
							|| this.datenaissInput.getValue() == null || this.emailInput.getText().trim().equals("")
							|| this.domaineInput.getText().trim().equals("")
							|| this.passInput.getText().trim().equals("") || this.roleInput.getValue() == null) {
						this.showDialog(AlertType.WARNING, "Erreur", "Veuillez remplir le formulaire");
						return;
					}

					// form validation: password validation
					if (!this.passInput.getText().equals(this.confirmPassInput.getText())) {
						this.showDialog(AlertType.WARNING, "Erreur",
								"Le mot de passe et le mot de passe de confirmation ne correspondent pas.");
						return;
					}

					Connection c = DbConnect.getInstance().getConnection();

					String sql = "INSERT INTO adherents (cin, nom, prenom, sexe, profession, tel, date_naissance, date_inscription, email, domain_interet, motpass, role) values (?,?,?,?,?,?,?,?,?,?,?,?);";
					PreparedStatement stmt = c.prepareStatement(sql);

					stmt.setInt(1, Integer.parseInt(this.cinInput.getText()));
					stmt.setString(2, this.nomInput.getText());
					stmt.setString(3, this.prenomInput.getText());
					stmt.setString(4, this.sexeInput.getValue());
					stmt.setString(5, this.profInput.getText());
					stmt.setInt(6, Integer.parseInt(this.telInput.getText()));
					stmt.setDate(7, Date.valueOf(LocalDate.of(this.datenaissInput.getValue().getYear(),
							this.datenaissInput.getValue().getMonthValue(),
							this.datenaissInput.getValue().getDayOfMonth())));
					stmt.setDate(8, new Date(System.currentTimeMillis()));
					stmt.setString(9, this.emailInput.getText());
					stmt.setString(10, this.domaineInput.getText());
					stmt.setString(11, this.passInput.getText());
					stmt.setString(12, this.roleInput.getValue());
					stmt.execute();

					c.close();

					this.initTable();

					this.close_window(event);

				} catch (Exception e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
				}
			});
		}

		// when cancel btn clicked
		if (this.cancelBtn != null) {
			cancelBtn.setOnMouseClicked(event -> {
				this.close_window(event);
			});
		}

		// when modify btn clicked
		if (this.editBtn != null) {
			editBtn.setOnMouseClicked(event -> {
				try {

					// form validation
					if (this.nomInput.getText().trim().equals("") || this.prenomInput.getText().trim().equals("")
							|| this.cinInput.getText().trim().equals("") || this.sexeInput.getValue() == null
							|| this.profInput.getText().trim().equals("") || this.telInput.getText().trim().equals("")
							|| this.datenaissInput.getValue() == null || this.emailInput.getText().trim().equals("")
							|| this.domaineInput.getText().trim().equals("")
							|| this.passInput.getText().trim().equals("") || this.roleInput.getValue() == null) {
						this.showDialog(AlertType.WARNING, "Erreur", "Veuillez remplir le formulaire");
						return;
					}

					// form validation: password validation
					if (!this.passInput.getText().equals(this.confirmPassInput.getText())) {
						this.showDialog(AlertType.WARNING, "Erreur",
								"Le mot de passe et le mot de passe de confirmation ne correspondent pas.");
						return;
					}

					Connection c = DbConnect.getInstance().getConnection();

					String sql = "UPDATE adherents SET cin = ?, nom = ?, prenom = ?, sexe = ?, profession = ?, tel = ?, date_naissance = ?, email = ?, domain_interet = ?, motpass = ?, role = ? WHERE num = ?;";
					PreparedStatement stmt = c.prepareStatement(sql);

					stmt.setInt(1, Integer.parseInt(this.cinInput.getText()));
					stmt.setString(2, this.nomInput.getText());
					stmt.setString(3, this.prenomInput.getText());
					stmt.setString(4, this.sexeInput.getValue());
					stmt.setString(5, this.profInput.getText());
					stmt.setInt(6, Integer.parseInt(this.telInput.getText()));

					System.out.println("=================");
					System.out.println(this.datenaissInput.getValue());
					System.out.println("=================");
					stmt.setObject(7, this.datenaissInput.getValue());
					stmt.setString(8, this.emailInput.getText());
					stmt.setString(9, this.domaineInput.getText());
					stmt.setString(10, this.passInput.getText());
					stmt.setString(11, this.roleInput.getValue());
					stmt.setInt(12, this.selectedAdherentId);
					stmt.execute();

					c.close();

					this.initTable();

					this.close_window(event);

				} catch (Exception e) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
				}
			});

		}

	}

	/**
	 * Show Modal
	 */
	@FXML
	void addAdherent(ActionEvent event) throws IOException {
		Scene scene = new Scene(loadFXML("ajoutAdherent"));
		Stage stage = new Stage();

		stage.setScene(scene);
		stage.setTitle("Ajouter un adherent");
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

	void close_window(MouseEvent event) {
		// close window
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	void showDialog(AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);

		alert.setTitle(title);
		alert.initOwner(this.roleInput.getScene().getWindow());
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	void initTable() throws SQLException {

		this.adherentsTable.getItems().clear();
		this.AdherentData.clear();

		if (this.adherentsTable != null) {

			this.numCol.setCellValueFactory(new PropertyValueFactory<>("num"));
			this.nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
			this.prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			this.roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
			this.emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
			this.datenaissanceCol.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));

			this.actionsCol.setCellFactory(tc -> new TableCell<Adherent, Adherent>() {
				Button edit = new Button("");
				Button supp = new Button("");

				Tooltip tooltip1 = new Tooltip("Modifier");
				Tooltip tooltip2 = new Tooltip("Supprimer");

				HBox buttons = new HBox(5, edit, supp);

				@Override
				protected void updateItem(Adherent person, boolean empty) {

					edit.setTooltip(tooltip1);
					supp.setTooltip(tooltip2);

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
						alert.setTitle("Supprimer Adhérent");
						alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet adhérent?");
						alert.initOwner(supp.getScene().getWindow());

						// option != null.
						Optional<ButtonType> option = alert.showAndWait();

						if (option.get() == null) {

						} else if (option.get() == ButtonType.OK) {
							int userId = getTableRow().getItem().getNum();

							try {
								deleteAdherent(userId);
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

					super.updateItem(person, empty);
					setGraphic(empty ? null : buttons);
				}

			});

			// get list of users
			Connection connection = DbConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM adherents ORDER BY num DESC";
			// System.out.println(sql);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Adherent adherent = new Adherent(rs.getInt("num"), rs.getInt("cin"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("sexe"), rs.getString("profession"), rs.getInt("tel"),
						rs.getObject("date_naissance", LocalDate.class),
						rs.getObject("date_inscription", LocalDate.class), rs.getString("email"),
						rs.getString("domain_interet"), rs.getString("motpass"), rs.getString("role"));

				AdherentData.add(adherent);
			}

			statement.close();
			connection.close();

			this.adherentsTable.getItems().addAll(AdherentData);

			// System.out.println("data length:" + this.AdherentData.size());
		}
	}

	public void deleteAdherent(int AdherentId) throws SQLException {
		Connection connection = DbConnect.getInstance().getConnection();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM adherents WHERE num = ?");
		statement.setInt(1, AdherentId);
		statement.executeUpdate();

	}

	public void openEditModal(Adherent ad) throws IOException {

		this.selectedAdherentId = ad.getNum();

		Scene scene = new Scene(loadFXML("modifAdherent"));
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Modifier Adhérent");
		// Add icon to application
		Image img = new Image(App.class.getResourceAsStream("imgs/logo.png"));
		stage.getIcons().add(img);
		stage.setResizable(false);
		stage.show();

		// System.out.println(ad);

		this.nomInput.setText(ad.getNom());
		this.prenomInput.setText(ad.getPrenom());
		this.sexeInput.setValue(ad.getSexe());
		this.profInput.setText(ad.getProfession());
		this.telInput.setText(Integer.toString(ad.getTel()));
		this.datenaissInput.setValue(ad.getDate_naissance());

		this.emailInput.setText(ad.getEmail());
		this.domaineInput.setText(ad.getDomain_interet());
		this.passInput.setText(ad.getMotpass());
		this.roleInput.setValue(ad.getRole());
		this.cinInput.setText(Integer.toString(ad.getCin()));
		this.confirmPassInput.setText(ad.getMotpass());
	}

}
