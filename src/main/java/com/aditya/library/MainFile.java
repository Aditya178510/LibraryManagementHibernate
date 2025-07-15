package com.aditya.library;

import java.util.List;
import java.util.Scanner;

public class MainFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDBManager manager = new BookDBManager();

        int choice;
        do {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. List All Books");
            System.out.println("3. Get Book By ID");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    manager.addBook(new Book(title, author));
                }
                case 2 -> {
                    List<Book> books = manager.getAllBooks();
                    books.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Enter book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Book book = manager.getBookById(id);
                    System.out.println(book != null ? book : "Book not found.");
                }
                case 4 -> {
                    System.out.print("Enter book ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Book book = manager.getBookById(id);
                    if (book != null) {
                        System.out.print("Enter new title: ");
                        book.setTitle(scanner.nextLine());
                        System.out.print("Enter new author: ");
                        book.setAuthor(scanner.nextLine());
                        manager.updateBook(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                }
                case 5 -> {
                    System.out.print("Enter book ID to delete: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    manager.deleteBook(id);
                }
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);

        manager.close();
        scanner.close();
    }
}
