package testPkg.utils;

import java.io.File;

public class FileUtils {
    
    public static File createDirectory(String parentPath, String dirName) {
        if (parentPath == null || parentPath.isEmpty()) {
            throw new IllegalArgumentException("Parent path cannot be null or empty");
        }
        
        File parentDir = new File(parentPath);
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }

        File dir = new File(parentDir, dirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static void main(String[] args) {
        // Example Usage
        String dynamicParentPath = "D:\\autoTemp\\text"; 
        File createdDir = createDirectory(dynamicParentPath, String.valueOf(System.currentTimeMillis()));
        System.out.println("Directory created: " + createdDir.getAbsolutePath());
    }

}
