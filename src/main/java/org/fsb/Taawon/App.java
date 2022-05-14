package org.fsb.Taawon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

/** 
 * JavaFX App 
 */  
public class App extends Application {

    private static Scene scene;       
  
    @Override
    public void start(Stage stage)  throws IOException {
    
        scene = new Scene(loadFXML("login")); 
        
        stage.setScene(scene);
        stage.setTitle("Taawon - تعاون");
        stage.setResizable(false);
        stage.centerOnScreen();
        // Add icon to application
        Image img = new Image(App.class.getResourceAsStream("imgs/logo.png"));
        stage.getIcons().add(img);
        
     	// Open login page
        stage.show(); 
    }    
    
    
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void playSound(String filename) {

        // play audio even if scene is not visible
    	Media media = new Media(App.class.getResource("sounds/" + filename).toString()); 
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

    }

}