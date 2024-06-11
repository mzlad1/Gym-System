module com.example.projectdatabase12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires layout;
    requires kernel;
    requires io;
    requires itextpdf;
	requires javafx.graphics;


    opens com.example.projectdatabase12 to javafx.fxml;
    exports com.example.projectdatabase12;
}