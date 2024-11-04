import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        LibraryFacade libraryFacade = new LibraryFacade();
        NotificationFactory notificationFactory = new NotificationFactory();
        BookDueNotifier dueNotifier = new BookDueNotifier();


        User user1 = new User("User1");
        dueNotifier.addObserver(new UserObserver("User1"));


        Book book1 = new Book("978-3-16-148410-0", "Design Patterns", "Gamma");
        Book book2 = new Book("978-0-321-12742-6", "Effective Java", "Bloch");
        libraryFacade.addBook(book1);
        libraryFacade.addBook(book2);


        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        System.out.println("Welcome to the Library Management System");
        System.out.println("Available Books:");
        books.forEach(book -> System.out.println(book));


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Borrow a book");
            System.out.println("2. Return a book");
            System.out.println("3. View notifications");
            System.out.println("4. Sort books by title");
            System.out.println("5. Sort books by author");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Borrow a book
                    System.out.print("Enter ISBN to borrow: ");
                    String isbnBorrow = scanner.next();
                    libraryFacade.borrowBook(user1, isbnBorrow);
                    dueNotifier.notifyObservers("Your borrowed book " + isbnBorrow + " is due soon.");
                    break;

                case 2: // Return a book
                    System.out.print("Enter ISBN to return: ");
                    String isbnReturn = scanner.next();
                    libraryFacade.returnBook(user1, isbnReturn);
                    break;

                case 3: // View notifications
                    Notification notification = notificationFactory.createNotification("Email");
                    notification.send("This is a due date reminder.");
                    break;

                case 4: // Sort by title
                    SortStrategy titleSort = new TitleSortStrategy();
                    titleSort.sort(books);
                    System.out.println("Books sorted by title:");
                    books.forEach(book -> System.out.println(book));
                    break;

                case 5: // Sort by author
                    SortStrategy authorSort = new AuthorSortStrategy();
                    authorSort.sort(books);
                    System.out.println("Books sorted by author:");
                    books.forEach(book -> System.out.println(book));
                    break;

                case 6: // Exit
                    System.out.println("Exiting the system. Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
