public class LibraryFacade {
    private BookService bookService;
    private UserService userService;
    private BorrowingService borrowingService;

    public LibraryFacade() {
        this.bookService = new BookService();
        this.userService = new UserService();
        this.borrowingService = new BorrowingService(bookService);
    }
    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public void borrowBook(User user, String isbn) {
        if (borrowingService.isAvailable(isbn)) {
            borrowingService.borrowBook(user, isbn);
        }
    }

    public void returnBook(User user, String isbn) {
        borrowingService.returnBook(user, isbn);
    }
}