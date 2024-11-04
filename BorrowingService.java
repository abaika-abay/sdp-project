class BorrowingService {
    private List<BorrowRecord> borrowRecords;
    private BookService bookService;

    public BorrowingService(BookService bookService) {
        this.bookService = bookService;  // This constructor requires a BookService object
        this.borrowRecords = new ArrayList<>();
    }
    public boolean isAvailable(String isbn) {
        // Check if the book is borrowed
        for (BorrowRecord record : borrowRecords) {
            if (record.getBook().getIsbn().equals(isbn) && !record.isReturned()) {
                return false;  // The book is currently borrowed and not returned
            }
        }
        return true; // The book is available for borrowing
    }
    public void borrowBook(User user, String isbn) {
        Book book = bookService.findBookByIsbn(isbn);
        if (book != null) {
            BorrowRecord record = new BorrowRecord(user, book);
            borrowRecords.add(record);
            System.out.println(user.getName() + " borrowed the book: " + book.getTitle());
        } else {
            System.out.println("Book with ISBN " + isbn + " not available for borrowing.");
        }
    }

    public void returnBook(User user, String isbn) {
        for (BorrowRecord record : borrowRecords) {
            if (record.getUser().equals(user) && record.getBook().getIsbn().equals(isbn) && !record.isReturned()) {
                record.markReturned();
                System.out.println(user.getName() + " returned the book: " + record.getBook().getTitle());
                return;
            }
        }
        System.out.println("No matching record found for returning the book with ISBN " + isbn + ".");
    }

    public List<BorrowRecord> getUserBorrowRecords(User user) {
        List<BorrowRecord> userRecords = new ArrayList<>();
        for (BorrowRecord record : borrowRecords) {
            if (record.getUser().equals(user)) {
                userRecords.add(record);
            }
        }
        return userRecords;
    }
}
