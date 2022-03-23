package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class viewOne {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextArea textarea;


    @FXML
    void initialize() {
    	   assert textarea != null : "fx:id=\"textarea\" was not injected: check your FXML file 'view1.fxml'.";
    }
    
    @FXML
    void test(ActionEvent event) {
    	String mytest = this.textarea.getText();
    	System.out.println(mytest);
    }

}
