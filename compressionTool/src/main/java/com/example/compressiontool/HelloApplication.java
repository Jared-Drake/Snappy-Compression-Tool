package com.example.compressiontool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class HelloApplication extends Application {

    private File selectedFile;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snappy File Compression Tool");

        // Drop area
        Label dropLabel = new Label("Drag and drop files here");
        dropLabel.setStyle("-fx-font-size: 14px; -fx-border-color: black; -fx-padding: 10px;");
        Rectangle dropArea = new Rectangle(300, 100, Color.LIGHTGRAY);
        dropArea.setOnDragOver(event -> {
            event.acceptTransferModes(javafx.scene.input.TransferMode.COPY);
            event.consume();
        });

        dropArea.setOnDragDropped(event -> {
            List<File> files = event.getDragboard().getFiles();
            if (files.size() > 0) {
                selectedFile = files.get(0);
                dropLabel.setText("Selected: " + selectedFile.getName());
            }
            event.consume();
        });

        // Buttons
        Button compressButton = new Button("Compress");
        compressButton.setOnAction(event -> {
            if (selectedFile != null) {
                SnappyCompressionTool.compressFile(selectedFile.getAbsolutePath(), selectedFile.getAbsolutePath() + ".snappy");
                dropLabel.setText("File compressed: " + selectedFile.getName() + ".snappy");
            } else {
                dropLabel.setText("No file selected for compression.");
            }
        });

        Button decompressButton = new Button("Decompress");
        decompressButton.setOnAction(event -> {
            if (selectedFile != null) {
                String outputFile = selectedFile.getAbsolutePath().replace(".snappy", "");
                SnappyDecompressionTool.decompressFile(selectedFile.getAbsolutePath(), outputFile);
                dropLabel.setText("File decompressed to: " + outputFile);
            } else {
                dropLabel.setText("No file selected for decompression.");
            }
        });

        // Layout
        VBox root = new VBox(10, dropLabel, dropArea, compressButton, decompressButton);
        root.setStyle("-fx-padding: 20px; -fx-alignment: center; -fx-spacing: 10px;");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
