package com.LibrarymanagemntSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        Library library = new Library();

        // Load library items from file
        List<LibraryItem> libraryItems = LibraryIO.loadItemsFromFile("itemlist.lms");
        for (LibraryItem item : libraryItems) {
            library.addItem(item);
        }

        // Load users from file
        List<User> users = LibraryIO.loadUserListFromFile("userlist.lms");
        for (User user : users) {
            library.addUser(user);
        }

        // Load borrowed items from file
        Map<String, String> borrowedItems = LibraryIO.loadBorrowedItemsFromFile("borroweditems.lms");
        for (Map.Entry<String, String> borrowedItem : borrowedItems.entrySet()) {
            library.getBorrowedItems().put(borrowedItem.getKey(), borrowedItem.getValue());
        }

        // Display library items
        System.out.println("Please find the list of all library items:");
        library.getLibraryItems().forEach(item ->
                System.out.println(item.getTitle() + "\t" + item.getAuthor() + "\t" + item.getSerialNumber()));
        System.out.println("----------------------------------------------------------");

        // Display borrowed items
        System.out.println("Please find the list of borrowed items from the library:");
        library.getBorrowedItems().forEach((item, user) -> System.out.println(item + " : " + user));
        System.out.println("---------------------------------------------------------");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;

        while (!exit) {
            try {
                // Main menu options
                System.out.println("Enter the main option:");
                System.out.println("1. Create a new item");
                System.out.println("2. Create a new user");
                System.out.println("3. Exit");
                int mainOption = Integer.parseInt(reader.readLine());

                switch (mainOption) {
                    case 1 -> {
                        System.out.println("Which item do you need to create?");
                        System.out.println("1. Book");
                        System.out.println("2. Magazine");
                        int createItemType = Integer.parseInt(reader.readLine());

                        if (createItemType == 1) {
                            System.out.println("Please enter the book name:");
                            String bookName = reader.readLine();

                            System.out.println("Please enter the book author:");
                            String bookAuthor = reader.readLine();

                            System.out.println("Please enter the book serial number:");
                            String bookSerialNumber = reader.readLine();

                            // Check if serial number already exists
                            if (library.getLibraryItems().stream().anyMatch(li -> Objects.equals(li.getSerialNumber(), bookSerialNumber))) {
                                System.out.println("This serial number is already entered!");
                            } else {
                                LibraryItem createBook = new Book(bookName, bookAuthor, bookSerialNumber);
                                library.addItem(createBook);
                                System.out.println("Book successfully added to the library.");
                            }
                        } else if (createItemType == 2) {
                            System.out.println("Please enter the magazine name:");
                            String magazineName = reader.readLine();

                            System.out.println("Please enter the magazine author:");
                            String magazineAuthor = reader.readLine();

                            System.out.println("Please enter the magazine serial number:");
                            String magazineSerialNumber = reader.readLine();

                            // Check if serial number already exists
                            if (library.getLibraryItems().stream().anyMatch(li -> Objects.equals(li.getSerialNumber(), magazineSerialNumber))) {
                                System.out.println("This serial number is already entered!");
                            } else {
                                LibraryItem createMagazine = new Magazine(magazineName, magazineAuthor, magazineSerialNumber);
                                library.addItem(createMagazine);
                                System.out.println("Magazine successfully added to the library.");
                            }
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                    case 2 -> {
                        System.out.println("Please enter the user name:");
                        String userName = reader.readLine();

                        System.out.println("Please enter the user ID:");
                        String userID = reader.readLine();

                        User user = new User(userName, userID);
                        library.addUser(user);
                        System.out.println("User successfully added to the library.");
                    }
                    case 3 -> exit = true;
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        System.out.println("Exiting the Library Management System. Goodbye!");
    }
}
