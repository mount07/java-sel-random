package testPkg.misc;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileDir {
	static String path = "F:\\bulkdoc";

	public static void main(String[] args) throws InterruptedException {

		renameFilesInSubDir(path);
		removeNumbersInDirName(path);
		moveFilesForSameDirName(path);
		deleteEmptyDir(path);
	}

	public static void findSubDir(String path) {
		File file = new File(path);
		String[] directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});

		System.out.println(Arrays.toString(directories));
	}

	public static void findSubDirAndFiles(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File : " + listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory : " + listOfFiles[i].getName());
			}
		}
	}

	public static void renameFilesInSubDir(String path) throws InterruptedException {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		List<String> dirNames = new ArrayList<String>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()) {
				System.out.println("" + listOfFiles[i].getName());
				dirNames.add(listOfFiles[i].getName());
			}
		}

		for (String dirName : dirNames) {
			String dirPath = path + "\\" + dirName;
			System.out.println("SubDir Path : " + dirPath);

			String newName = dirName.split(" - ")[1].trim();
			System.out.println("NewName : " + newName);

			renameFiles(newName, dirPath);

			Thread.sleep(1000);
		}

	}

	public static void renameFiles(String newName, String path) throws InterruptedException {
		File dir = new File(path);
		File[] listOfFiles = dir.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {

				File f = new File(path + "\\" + listOfFiles[i].getName());
				System.out.println("FileIn : " + path + "\\" + listOfFiles[i].getName());

				f.renameTo(new File(path + "\\" + newName + " - " + listOfFiles[i].getName()));
				System.out.println("FileOut : " + path + "\\" + newName + " - " + listOfFiles[i].getName());

				System.out.println("renamed...");
				Thread.sleep(100);
			}
		}
	}

	public static void removeNumbersInDirName(String path) throws InterruptedException {
		File folder = new File(path);
		File[] listOfItems = folder.listFiles();

		for (int i = 0; i < listOfItems.length; i++) {
			if (listOfItems[i].isDirectory()) {
				System.out.println("" + listOfItems[i].getName());

				File f = new File(path + "\\" + listOfItems[i].getName());
				System.out.println("FileIn : " + path + "\\" + listOfItems[i].getName());

				f.renameTo(new File(
						path + "\\" + listOfItems[i].getName().replaceAll("\\d", "").replaceAll("-", "").trim()));
				System.out.println("FileOut : " + path + "\\"
						+ listOfItems[i].getName().replaceAll("\\d", "").replaceAll("-", "").trim());

				Thread.sleep(500);
			}
		}
	}
	
	public static void moveFilesForSameDirName(String path) throws InterruptedException {
		File folder = new File(path);
		File[] listOfItems = folder.listFiles();
		
		File dir;
		
		for (int i = 0; i < listOfItems.length; i++) {
			if (listOfItems[i].isDirectory()) {
				System.out.println("" + listOfItems[i].getName());

				File f = new File(path + "\\" + listOfItems[i].getName());
				System.out.println("FileIn : " + path + "\\" + listOfItems[i].getName());
				
				String newName = listOfItems[i].getName().replaceAll("\\d", "").replaceAll("-", "").trim();
				dir = new File(path + "\\" + newName);
				if (dir.exists()) {
					String sourcePath = path + "\\" + listOfItems[i].getName();
					String destPath = path + "\\" + newName;
					mergeTwoDirectories(sourcePath, destPath);
				}

				f.renameTo(new File(path + "\\" + listOfItems[i].getName().replaceAll("\\d", "").replaceAll("-", "").trim()));
				System.out.println("FileOut : " + path + "\\" + listOfItems[i].getName().replaceAll("\\d", "").replaceAll("-", "").trim());

				Thread.sleep(500);
			}
		}
	}

	public static void mergeTwoDirectories(String sourceDir1Path, String sourceDir2Path) {
		File dir1 = new File(sourceDir1Path);
		File dir2 = new File(sourceDir2Path);
		String targetDirPath = dir2.getAbsolutePath();
		File[] files = dir1.listFiles();
		for (File file : files) {
			file.renameTo(new File(targetDirPath + File.separator + file.getName()));
			System.out.println(file.getName() + " is moved!");
		}
	}
	
	private static void deleteEmptyDir(String path) {
		boolean isFinished = false;
	    File folder = new File(path);
	    File[] listofFiles = folder.listFiles();
	    if (listofFiles.length == 0) {
	        System.out.println("Folder Name :: " + folder.getAbsolutePath() + " is deleted.");
	        folder.delete();
	        isFinished = false;
	    } else {
	        for (int j = 0; j < listofFiles.length; j++) {
	            File file = listofFiles[j];
	            if (file.isDirectory()) {
	            	deleteEmptyDir(file.getAbsolutePath());
	            }
	        }
	    }
	}

}
