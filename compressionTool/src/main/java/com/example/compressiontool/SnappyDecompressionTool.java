package com.example.compressiontool;

import org.xerial.snappy.Snappy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

public class SnappyDecompressionTool {

    /**
     * Decompress a file that was compressed using the Snappy algorithm.
     *
     * @param compressedFilePath The path to the compressed file.
     * @param outputFilePath     The path to the decompressed output file.
     */
    public static void decompressFile(String compressedFilePath, String outputFilePath) {
        try (
                FileInputStream fis = new FileInputStream(compressedFilePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                FileOutputStream fos = new FileOutputStream(outputFilePath)
        ) {
            // Read the compressed file into memory
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            // Decompress the file content
            byte[] decompressedData = Snappy.uncompress(baos.toByteArray());

            // Write the decompressed data to the output file
            fos.write(decompressedData);
            System.out.println("File successfully decompressed to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error during decompression: " + e.getMessage());
        }
    }
}
