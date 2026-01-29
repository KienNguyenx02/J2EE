package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Book> bookList = new ArrayList<>();

        // Thêm một vài dữ liệu mẫu
        bookList.add(new Book(1, "Lập trình Java Core", "John Doe", 150000));
        bookList.add(new Book(2, "Lập trình Web với Spring Boot", "Jane Smith", 250000));
        bookList.add(new Book(3, "Cấu trúc dữ liệu và giải thuật", "Peter Pan", 180000));
        bookList.add(new Book(4, "Lập trình Python cho người mới bắt đầu", "Guido van Rossum", 120000));


        int choice;
        do {
            System.out.println("\n----------- MENU QUẢN LÝ SÁCH -----------");
            System.out.println("1. Thêm 1 cuốn sách");
            System.out.println("2. Xóa 1 cuốn sách (theo mã)");
            System.out.println("3. Thay đổi thông tin 1 cuốn sách (theo mã)");
            System.out.println("4. Xuất thông tin tất cả các cuốn sách");
            System.out.println("5. Tìm các cuốn sách có tựa đề chứa 'Lập trình'");
            System.out.println("6. Tìm sách theo giá");
            System.out.println("7. Tìm sách theo danh sách tác giả");
            System.out.println("0. Thoát chương trình");
            System.out.println("-------------------------------------------");
            System.out.print("Mời bạn chọn chức năng: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addBook(bookList, scanner);
                case 2 -> deleteBook(bookList, scanner);
                case 3 -> updateBook(bookList, scanner);
                case 4 -> displayAllBooks(bookList);
                case 5 -> findBookByTitle(bookList);
                case 6 -> findBookByPrice(bookList, scanner);
                case 7 -> findBookByAuthors(bookList, scanner);
                case 0 -> System.out.println("Đã thoát chương trình. Tạm biệt!");
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 0);

        scanner.close();
    }

    // 1. Thêm sách
    public static void addBook(List<Book> bookList, Scanner scanner) {
        System.out.println("\n--- Thêm sách mới ---");
        Book newBook = new Book();
        newBook.input(scanner);
        bookList.add(newBook);
        System.out.println("=> Thêm sách thành công!");
    }

    // 2. Xóa sách
    public static void deleteBook(List<Book> bookList, Scanner scanner) {
        System.out.println("\n--- Xóa sách ---");
        System.out.print("Nhập mã sách cần xóa: ");
        int idToDelete = scanner.nextInt();
        scanner.nextLine();

        boolean removed = bookList.removeIf(book -> book.getId() == idToDelete);

        if (removed) {
            System.out.println("=> Đã xóa sách có mã " + idToDelete);
        } else {
            System.out.println("=> Không tìm thấy sách có mã " + idToDelete);
        }
    }

    // 3. Cập nhật sách
    public static void updateBook(List<Book> bookList, Scanner scanner) {
        System.out.println("\n--- Cập nhật thông tin sách ---");
        System.out.print("Nhập mã sách cần cập nhật: ");
        int idToUpdate = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Book book : bookList) {
            if (book.getId() == idToUpdate) {
                System.out.println("Nhập thông tin mới cho sách (ID cũ là " + idToUpdate + " sẽ được giữ nguyên):");
                String oldTitle = book.getTitle();
                // book.input(scanner) sẽ yêu cầu nhập lại ID, ta ghi đè lại
                book.input(scanner);
                book.setId(idToUpdate); // Giữ lại ID cũ
                System.out.println("=> Cập nhật sách '"+ oldTitle + "' thành công!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("=> Không tìm thấy sách có mã " + idToUpdate);
        }
    }

    // 4. Hiển thị tất cả sách
    public static void displayAllBooks(List<Book> bookList) {
        System.out.println("\n--- Danh sách tất cả sách ---");
        if (bookList.isEmpty()) {
            System.out.println("=> Danh sách trống.");
            return;
        }
        printBookHeader();
        bookList.forEach(Book::output);
        printBookFooter();
    }

    // 5. Tìm sách theo tựa đề "Lập trình"
    public static void findBookByTitle(List<Book> bookList) {
        System.out.println("\n--- Các sách có tựa đề chứa 'Lập trình' ---");
        List<Book> foundBooks = bookList.stream()
                .filter(book -> book.getTitle().toLowerCase().contains("lập trình"))
                .collect(Collectors.toList());

        if (foundBooks.isEmpty()) {
            System.out.println("=> Không tìm thấy sách nào phù hợp.");
        } else {
            printBookHeader();
            foundBooks.forEach(Book::output);
            printBookFooter();
        }
    }

    // 6. Tìm sách theo giá
    public static void findBookByPrice(List<Book> bookList, Scanner scanner) {
        System.out.println("\n--- Tìm sách theo giá ---");
        System.out.print("Nhập số lượng sách tối đa cần tìm (K): ");
        int k = scanner.nextInt();
        System.out.print("Nhập mức giá tối đa (P): ");
        double p = scanner.nextDouble();
        scanner.nextLine();

        List<Book> foundBooks = bookList.stream()
                .filter(book -> book.getPrice() <= p)
                .limit(k)
                .collect(Collectors.toList());

        if (foundBooks.isEmpty()) {
            System.out.println("=> Không tìm thấy sách nào có giá <= " + p);
        } else {
            System.out.println("\n--- Tìm thấy " + foundBooks.size() + " sách có giá <= " + p + " (hiển thị tối đa " + k + " sách) ---");
            printBookHeader();
            foundBooks.forEach(Book::output);
            printBookFooter();
        }
    }

    // 7. Tìm sách theo tác giả
    public static void findBookByAuthors(List<Book> bookList, Scanner scanner) {
        System.out.println("\n--- Tìm sách theo tác giả ---");
        System.out.print("Nhập danh sách tác giả (cách nhau bởi dấu phẩy): ");
        String authorsInput = scanner.nextLine();
        
        // Tách chuỗi và loại bỏ khoảng trắng thừa
        List<String> authors = Arrays.stream(authorsInput.split(","))
                                     .map(String::trim)
                                     .collect(Collectors.toList());

        List<Book> foundBooks = bookList.stream()
                .filter(book -> authors.stream().anyMatch(author -> book.getAuthor().equalsIgnoreCase(author)))
                .collect(Collectors.toList());

        if (foundBooks.isEmpty()) {
            System.out.println("=> Không tìm thấy sách của các tác giả đã nhập.");
        } else {
            System.out.println("\n--- Tìm thấy sách của các tác giả: " + String.join(", ", authors) + " ---");
            printBookHeader();
            foundBooks.forEach(Book::output);
            printBookFooter();
        }
    }

    // Helper methods for printing table
    public static void printBookHeader() {
        System.out.println("+------------+--------------------------------+---------------------------+-----------------+");
        System.out.println("| Mã Sách    | Tên Sách                       | Tác Giả                   | Đơn Giá         |");
        System.out.println("+------------+--------------------------------+---------------------------+-----------------+");
    }

    public static void printBookFooter() {
        System.out.println("+------------+--------------------------------+---------------------------+-----------------+");
    }
}
