module com.example.ce_216_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ce_216_project to javafx.fxml;
    exports com.example.ce_216_project;
}