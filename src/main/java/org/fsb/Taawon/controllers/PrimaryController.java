package org.fsb.Taawon.controllers;

import java.io.IOException;

import org.fsb.Taawon.App;
import org.fsb.Taawon.models.Adherent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrimaryController {

	@FXML
	private VBox content;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	// @FXML
	// private AnchorPane dashContent;

	@FXML
	private ImageView userImage;

	@FXML
	private Text tab1;

	@FXML
	private Text fullnameText;

	@FXML
	private Text roleText;

	@FXML
	private AnchorPane imagePane;

	@FXML
	private AnchorPane logoutPane;

	@FXML
	void homeOnClick(MouseEvent event) throws IOException {
		this.content.getChildren().clear();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("dashboard.fxml"));
		Node childNode = (Node) fxmlLoader.load();
		this.content.getChildren().add(childNode);
		VBox.setVgrow(childNode, Priority.ALWAYS);
	}

	@FXML
	void tab1onclick(MouseEvent event) throws IOException {
		this.content.getChildren().clear();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("dashboard.fxml"));
		Node childNode = (Node) fxmlLoader.load();
		this.content.getChildren().add(childNode);
		this.content.setVgrow(childNode, Priority.ALWAYS);

	}

	@FXML
	void tab2onclick(MouseEvent event) throws IOException {
		this.content.getChildren().clear();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("adherents.fxml"));
		Pane childNode = (Pane) fxmlLoader.load();
		this.content.getChildren().add(childNode);

		this.content.setVgrow(childNode, Priority.ALWAYS);

	}

	@FXML
	void tab3onclick(MouseEvent event) throws IOException {
		this.content.getChildren().clear();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("dons.fxml"));
		Pane childNode = (Pane) fxmlLoader.load();
		this.content.getChildren().add(childNode);

		this.content.setVgrow(childNode, Priority.ALWAYS);

	}

	@FXML
	void initialize() throws IOException {
		this.content.getChildren().clear();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("dashboard.fxml"));
		Node childNode = (Node) fxmlLoader.load();
		this.content.getChildren().add(childNode);
		VBox.setVgrow(childNode, Priority.ALWAYS);

		if (this.userImage != null) {
			// add contextmenu to imageview
			ContextMenu contextMenu = new ContextMenu();
			MenuItem aboutMenuItem = new MenuItem("À propos");
			MenuItem logoutMenuItem = new MenuItem("Déconnexion");
			aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("À propos");
				}
			});

			logoutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {

					closeCurrentScene();

					try {
						// open login window
						Scene loginScene = new Scene(FXMLLoader.load(App.class.getResource("login.fxml")));
						Stage loginStage = new Stage();

						loginStage.setScene(loginScene);
						loginStage.setTitle("Taawon - تعاون");
						loginStage.setResizable(false);
						loginStage.centerOnScreen();
						// Add icon to application
						Image img = new Image(App.class.getResourceAsStream("imgs/logo.png"));
						loginStage.getIcons().add(img);

						loginStage.setScene(loginScene);
						loginStage.show();
						App.playSound("logout.wav");

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});

			contextMenu.getItems().add(aboutMenuItem);
			contextMenu.getItems().add(logoutMenuItem);

			this.userImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

					if (event.getButton() == MouseButton.PRIMARY) {
						contextMenu.show(userImage, event.getScreenX(), event.getScreenY());
					}
				}
			});
		}
	
		if (this.logoutPane != null) {
			this.logoutPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					logout();
				}
			});
		}
	
	
	}

	void initData(Adherent adherent) {

		if (this.fullnameText != null) {
			this.fullnameText.setText(adherent.getNom() + " " + adherent.getPrenom());
			this.roleText.setText(adherent.getRole());
		}

	}

	void closeCurrentScene() {
		// get current scene
		Scene currentScene = this.userImage.getScene();
		// close current scene
		currentScene.getWindow().hide();
		System.out.println(currentScene);
	}

	void logout() {
		closeCurrentScene();

		try {
			// open login window
			Scene loginScene = new Scene(FXMLLoader.load(App.class.getResource("login.fxml")));
			Stage loginStage = new Stage();

			loginStage.setScene(loginScene);
			loginStage.setTitle("Taawon - تعاون");
			loginStage.setResizable(false);
			loginStage.centerOnScreen();
			// Add icon to application
			Image img = new Image(App.class.getResourceAsStream("imgs/logo.png"));
			loginStage.getIcons().add(img);

			loginStage.setScene(loginScene);
			loginStage.show();
			App.playSound("logout.wav");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@FXML
    void ouvrirEvent(MouseEvent event) throws IOException {
		this.content.getChildren().clear();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("events.fxml"));
		Pane childNode = (Pane) fxmlLoader.load();
		this.content.getChildren().add(childNode);

		this.content.setVgrow(childNode, Priority.ALWAYS);
    }


	@FXML
	void openNecessiteux(MouseEvent event) throws IOException {
		this.content.getChildren().clear();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("pauvres.fxml"));
		Pane childNode = (Pane) fxmlLoader.load();
		this.content.getChildren().add(childNode);

		this.content.setVgrow(childNode, Priority.ALWAYS);
	}

	@FXML
	void openFamille(MouseEvent event) throws IOException {
		this.content.getChildren().clear();
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("familles.fxml"));
		Pane childNode = (Pane) fxmlLoader.load();
		this.content.getChildren().add(childNode);

		this.content.setVgrow(childNode, Priority.ALWAYS);
	}

}
