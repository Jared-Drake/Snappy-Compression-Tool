module com.example.compressiontool {
    requires javafx.controls;
    requires javafx.fxml;
    requires snappy.java;


    opens com.example.compressiontool to javafx.fxml;
    exports com.example.compressiontool;
}