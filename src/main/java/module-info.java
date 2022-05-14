module org.fsb.Taawon {
	requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.media;
    
    
    opens org.fsb.Taawon to javafx.base, javafx.controls, javafx.fxml, javafx.graphics, javafx.media;
    opens org.fsb.Taawon.controllers to javafx.base, javafx.controls, javafx.fxml, javafx.graphics, javafx.media;
    opens org.fsb.Taawon.models to javafx.base, javafx.controls, javafx.fxml, javafx.graphics, javafx.media;
    // add "open ..." (new packages here)
   
    exports org.fsb.Taawon to javafx.base, javafx.controls, javafx.fxml, javafx.graphics, javafx.media;
    exports org.fsb.Taawon.controllers to javafx.base, javafx.controls, javafx.fxml, javafx.graphics, javafx.media;
    exports org.fsb.Taawon.models to javafx.base, javafx.controls, javafx.fxml, javafx.graphics, javafx.media;
    // add "exports ..." (new package here)
}
