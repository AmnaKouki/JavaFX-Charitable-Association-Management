package associationCaritative;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
       
    	FXMLLoader loader = new FXMLLoader();    	
    	loader.setLocation(getClass().getResource("/views/view1.fxml"));
    	
        VBox vbox = loader.<VBox>load();

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    	
    }

    public static void main(String[] args) {
        launch();
    }

}