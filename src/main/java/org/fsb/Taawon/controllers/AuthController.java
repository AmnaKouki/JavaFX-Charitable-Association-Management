package org.fsb.Taawon.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.fsb.Taawon.App;
import org.fsb.Taawon.models.Adherent;
import org.fsb.Taawon.utils.DbConnect;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AuthController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label login;

	@FXML
	private PasswordField passwordInput;

	@FXML
	private TextField userInput;

	private static Scene scene;

	private Adherent adherent;

	@FXML
	void login(MouseEvent event) throws IOException, SQLException {

		boolean isLogged = false;

		// get login stage
		Node source = (Node) event.getSource();
		Stage currentStage = (Stage) source.getScene().getWindow();

		Connection connection = DbConnect.getInstance().getConnection();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from adherents where email  = '"
				+ userInput.getText() + "' and motpass = '" + passwordInput.getText() + "'");

		if (resultSet.next()) {
			isLogged = true;

			this.adherent = new Adherent(
					resultSet.getInt("num"),
					resultSet.getInt("cin"),
					resultSet.getString("nom"),
					resultSet.getString("prenom"),
					resultSet.getString("sexe"),
					resultSet.getString("profession"),
					resultSet.getInt("tel"),
					resultSet.getObject("date_naissance", LocalDate.class),
					resultSet.getObject("date_inscription", LocalDate.class),
					resultSet.getString("email"),
					resultSet.getString("domain_interet"),
					resultSet.getString("motpass"),
					resultSet.getString("role"));
		} else {
			isLogged = false;
		}
		connection.close();

		if (isLogged) {

			// open new dashboard stage
			this.openDashboard();

			App.playSound("login.wav");

			currentStage.close();
		} else {
			this.showDialog(AlertType.WARNING, "Erreur", "Votre email ou mot de passe est incorrecte");
		}

	}

	private void openDashboard() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
		scene = new Scene(fxmlLoader.load());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Taawon - تعاون");
		// Add icon to application
		Image img = new Image(App.class.getResourceAsStream("imgs/logo.png"));
		stage.getIcons().add(img);

		PrimaryController controller = fxmlLoader.getController();
		controller.initData(this.adherent);
		stage.show();

		// pass data to stage
		// stage.setUserData(this.adherent);
	}

	@FXML
	void initialize() {
		assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'login.fxml'.";
		assert passwordInput != null : "fx:id=\"passwordInput\" was not injected: check your FXML file 'login.fxml'.";
		assert userInput != null : "fx:id=\"userInput\" was not injected: check your FXML file 'login.fxml'.";

		// REMOVE THIS
		 //this.userInput.setText("admin@gmail.com");
	     //this.passwordInput.setText("0000");
	}

	void showDialog(AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);

		alert.setTitle(title);
		alert.initOwner(this.userInput.getScene().getWindow());
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
