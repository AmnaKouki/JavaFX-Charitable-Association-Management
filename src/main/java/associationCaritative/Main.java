package associationCaritative;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
       
    	Parent rootNode = (Parent) FXMLLoader.load(getClass().getResource("/views/view1.fxml"));
        Scene scene = new Scene(rootNode);
        scene.getStylesheets().add("/styles/styles.css");
        stage.setScene(scene);
        stage.show();
        
    	
    }

    public static void main(String[] args) {
        launch();
    }

}