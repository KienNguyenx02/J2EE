package src;

import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;

    public Book() {
    }

    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // --- Getter and Setter ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // --- Input/Output Methods ---
    public void input(Scanner scanner) {
        // Input for ID with validation
        while (true) {
            try {
                System.out.print("Nhập mã sách: ");
                this.id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break; // Exit loop if input is valid
            } catch (java.util.InputMismatchException e) {
                System.out.println("=> Lỗi: Mã sách phải là một số nguyên. Vui lòng nhập lại.");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }
        }

        System.out.print("Nhập tên sách: ");
        this.title = scanner.nextLine();
        System.out.print("Nhập tác giả: ");
        this.author = scanner.nextLine();

        // Input for price with validation
        while (true) {
            try {
                System.out.print("Nhập đơn giá: ");
                this.price = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                break; // Exit loop if input is valid
            } catch (java.util.InputMismatchException e) {
                System.out.println("=> Lỗi: Đơn giá phải là một số. Vui lòng nhập lại.");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }
        }
    }

    public void output() {
        System.out.printf("| %-10d | %-30s | %-25s | %-15.2f |\n",
                this.id, this.title, this.author, this.price);
    }
}
