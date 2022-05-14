package org.fsb.Taawon.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.fsb.Taawon.utils.DbConnect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class DashboardController {
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private PieChart pie;

	@FXML
	private Text adText;

	@FXML
	private Text donsText;

	@FXML
	private Text famillesText;

	@FXML
	private Text necessiteuxText;

	@FXML
	private BarChart<?, ?> myBarChart;

	@FXML
    private CategoryAxis xAxe;

    @FXML
    private NumberAxis yAxe;

	@FXML
	void initialize() throws SQLException {
		assert pie != null : "fx:id=\"pie\" was not injected: check your FXML file 'dashboard.fxml'.";

		this.getStats();
	}

	void getStats() throws SQLException {
		Connection connection = DbConnect.getInstance().getConnection();

		/**
		 * SELECT (SELECT COUNT(*) FROM adherents WHERE ROLE = 'Administrateur') AS
		 * AdminCount,
		 * (SELECT COUNT(*) FROM adherents WHERE ROLE = 'Vice-précedent') AS ViceCount,
		 * (SELECT COUNT(*) FROM adherents WHERE ROLE = 'Secrétaire') AS
		 * SecretaireCount,
		 * (SELECT COUNT(*) FROM adherents WHERE ROLE = 'Trésorier') AS tresorierCount,
		 * (SELECT COUNT(*) FROM adherents WHERE ROLE = 'Collecteur des dons') AS
		 * collecteurCount,
		 * (SELECT COUNT(*) FROM adherents WHERE ROLE = 'Adhérent') AS adherentCount,
		 * (SELECT COUNT(*) FROM dons) AS donsTotal,
		 * (SELECT COUNT(*) FROM pauvres) AS pauvresTotal,
		 * (SELECT COUNT(*) FROM familles) AS famillesTotal,
		 * (SELECT COUNT(*) FROM adherents) AS AderentsTotalCount FROM adherents
		 */

		Statement statement = connection.createStatement();
		ResultSet resultStat = statement.executeQuery(
				" SELECT ( SELECT COUNT(*) FROM adherents WHERE ROLE = 'Administrateur') AS AdminCount, ( SELECT COUNT(*) FROM adherents WHERE ROLE = 'Vice-précedent') AS ViceCount, ( SELECT COUNT(*) FROM adherents WHERE ROLE = 'Secrétaire') AS SecretaireCount, ( SELECT COUNT(*) FROM adherents WHERE ROLE = 'Trésorier') AS tresorierCount, ( SELECT COUNT(*) FROM adherents WHERE ROLE = 'Collecteur des dons') AS collecteurCount, ( SELECT COUNT(*) FROM adherents WHERE ROLE = 'Adhérent') AS adherentCount, ( SELECT COUNT(*) FROM dons) AS donsTotal, ( SELECT COUNT(*) FROM pauvres) AS pauvresTotal, ( SELECT COUNT(*) FROM familles) AS famillesTotal, ( SELECT COUNT(*) FROM adherents) AS AderentsTotalCount, ( SELECT COUNT(*) FROM dons WHERE categorie = 'Vêtements') AS vetements, ( SELECT COUNT(*) FROM dons WHERE categorie = 'Don Alimentaire') AS alimentaire, ( SELECT COUNT(*) FROM dons WHERE categorie = 'Livres') AS livres, ( SELECT COUNT(*) FROM dons WHERE categorie = 'Médicaments') AS medicaments, ( SELECT COUNT(*) FROM dons WHERE categorie = 'Autres') AS autres FROM adherents");
		if (resultStat.next()) {
			System.out.println("AdminCount : " + resultStat.getInt("AdminCount"));
			System.out.println("ViceCount : " + resultStat.getInt("ViceCount"));
			System.out.println("SecretaireCount : " + resultStat.getInt("SecretaireCount"));
			System.out.println("tresorierCount : " + resultStat.getInt("tresorierCount"));
			System.out.println("collecteurCount : " + resultStat.getInt("collecteurCount"));
			System.out.println("adherentCount : " + resultStat.getInt("adherentCount"));

			
			XYChart.Series set1 = new XYChart.Series();
			set1.getData().add(new XYChart.Data("Vêtements", resultStat.getInt("vetements")));

			XYChart.Series set2 = new XYChart.Series();
			set2.getData().add(new XYChart.Data("Don Alimentaire", resultStat.getInt("alimentaire")));

			XYChart.Series set3 = new XYChart.Series();
			set3.getData().add(new XYChart.Data("Livres", resultStat.getInt("livres")));

			XYChart.Series set4 = new XYChart.Series();
			set4.getData().add(new XYChart.Data("Médicaments", resultStat.getInt("medicaments")));

			XYChart.Series set5 = new XYChart.Series();
			set5.getData().add(new XYChart.Data("Autres", resultStat.getInt("autres")));

			myBarChart.getData().addAll(set1, set2, set3, set4, set5);

			// change bar width
			myBarChart.setBarGap(0.1);

			// remove bar spacing
			myBarChart.setCategoryGap(0);

			// remove barchart legend
			myBarChart.setLegendVisible(false);

			// make text horizontal
			myBarChart.setLegendSide(Side.BOTTOM);

			// piechart text padding
			pie.setLabelLineLength(10);

			
		
			
		



			// ObservableList<String> xAxisData = FXCollections.observableArrayList("Vêtements", "Don Alimentaire", "Livres", "Médicaments", "Autres");
			// ObservableList<Number> yAxisData = FXCollections.observableArrayList(resultStat.getInt("vetements"), resultStat.getInt("alimentaire"), resultStat.getInt("livres"), resultStat.getInt("medicaments"), resultStat.getInt("autres"));
			// myBarChart.setTitle("Statistiques des dons");
			// xAxe.setLabel("Catégories");
			// yAxe.setLabel("Nombre de dons");
			// myBarChart.setData(FXCollections.observableArrayList(xAxisData, yAxisData));


			




			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
					new PieChart.Data("Administrateurs", resultStat.getInt("AdminCount")),
					new PieChart.Data("Vice-précedents", resultStat.getInt("ViceCount")),
					new PieChart.Data("Secrétaires", resultStat.getInt("SecretaireCount")),
					new PieChart.Data("Trésorier", resultStat.getInt("tresorierCount")),
					new PieChart.Data("Collecteur des dons", resultStat.getInt("collecteurCount")),
					new PieChart.Data("Adhérent", resultStat.getInt("adherentCount")));
			this.pie.setData(pieChartData);

			this.adText.setText(resultStat.getInt("AderentsTotalCount") + "");
			this.donsText.setText(resultStat.getInt("donsTotal") + "");
			this.famillesText.setText(resultStat.getInt("famillesTotal") + "");
			this.necessiteuxText.setText(resultStat.getInt("pauvresTotal") + "");

		}

		// this.roleInput.getItems().add("Administrateur");
		// this.roleInput.getItems().add("Vice-précedent");
		// this.roleInput.getItems().add("Secrétaire");
		// this.roleInput.getItems().add("Trésorier");
		// this.roleInput.getItems().add("Collecteur des dons");
		// this.roleInput.getItems().add("Adhérent");

	}

}
