package LockedMeProject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LockedMe {

	static final String FOLDER_PATH = "C:\\Users\\<your-username>\\Desktop\\LockedMe_Files\\";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int option = 0;

		displayWelcomeScreen();

		do {
			displayMainMenu();

			try {
				option = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number between 1 and 3.\n");
				continue;
			}

			switch (option) {
				case 1:
					displayFileNamesInDirectory();
					break;
				case 2:
					addFileToDirectory(scanner);
					break;
				case 3:
					searchFileInDirectory(scanner);
					break;
				case 4:
					System.out.println("Thank you for using LockedMe.");
					break;
				default:
					System.out.println("Invalid input. Please enter a number between 1 and 4.\n");
			}
		} while (option != 4);

		scanner.close();
	}

	public static void displayWelcomeScreen() {
		System.out.println("Welcome to LockedMe.");
		System.out.println("Developed by <Kenan Yurtisigi>");
		System.out.println("Version 1.0\n");
	}

	public static void displayMainMenu() {
		System.out.println("Please select an option:");
		System.out.println("1. View all files in directory");
		System.out.println("2. Add a file to directory");
		System.out.println("3. Search for a file in directory");
		System.out.println("4. Exit\n");
	}

	public static void displayFileNamesInDirectory() {
		List<String> fileNames = getFileNamesInDirectory();

		if (fileNames.size() == 0) {
			System.out.println("The directory is empty.\n");
		} else {
			System.out.println("Files in directory:");
			for (String fileName : fileNames) {
				System.out.println(fileName);
			}
			System.out.println();
		}
	}

	public static void addFileToDirectory(Scanner scanner) {
		System.out.println("Please enter the name of the file to add:");
		String fileName = scanner.nextLine();

		File file = new File(FOLDER_PATH + fileName);
		try {
			file.createNewFile();
			System.out.println("File created successfully.\n");
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage() + "\n");
		}
	}

	public static void searchFileInDirectory(Scanner scanner) {
		System.out.println("Please enter the name of the file to search for:");
		String fileName = scanner.nextLine();

		List<String> fileNames = getFileNamesInDirectory();
		Collections.sort(fileNames);
		int index = Collections.binarySearch(fileNames, fileName);

		if (index >= 0) {
			System.out.println("The file was found in the directory.\n");
		} else {
			System.out.println("The file was not found in the directory.\n");
		}
	}

	public static List<String> getFileNamesInDirectory() {
		File folder = new File(FOLDER_PATH);
		File[] files = folder.listFiles();
		List<String> fileNames = new ArrayList<String>();

		for (File file : files) {
			if (file.isFile()) {
				fileNames.add(file.getName());
			}
		}

		return fileNames;
	}
}