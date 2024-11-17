package com.example.compressiontool;

import org.xerial.snappy.Snappy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

public class SnappyCompressionTool {

    /**
     * Compress a file using the Snappy algorithm.
     *
     * @param inputFilePath  The path to the input file.
     * @param outputFilePath The path to the compressed output file.
     */
    public static void compressFile(String inputFilePath, String outputFilePath) {
        try (
                FileInputStream fis = new FileInputStream(inputFilePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                FileOutputStream fos = new FileOutputStream(outputFilePath)
        ) {
            // Read the input file into memory
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            // Compress the file content
            byte[] compressedData = Snappy.compress(baos.toByteArray());

            // Write the compressed data to the output file
            fos.write(compressedData);
            System.out.println("File successfully compressed to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error during compression: " + e.getMessage());
        }
    }
}
