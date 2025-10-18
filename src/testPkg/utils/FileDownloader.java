package testPkg.utils;

import java.io.File;
import java.io.IOException;

public class FileDownloader {
    
    public static void downloadFile(String url, String filename, String directory) throws IOException {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the directory if it doesn't exist
        }

        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "curl", url, "--output", filename);
        processBuilder.directory(dir); // Set the working directory
        
        Process process = processBuilder.start();
        
        try {
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("Download failed with exit code: " + exitCode);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Download interrupted", e);
        }
    }

    public static void downloadFile1(String url, String filename, File dir) throws IOException {
		Process process = Runtime.getRuntime().exec("cmd /c " + "curl " + url + " --output " + filename, null,	dir);
	}

    public static void main(String[] args) {
        try {
            downloadFile("https://example.com/file.zip", "downloaded.zip", "C:\\Downloads");
            System.out.println("Download completed successfully.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
