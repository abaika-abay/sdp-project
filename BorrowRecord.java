class BorrowRecord {
    private User user;
    private Book book;
    private boolean isReturned;

    public BorrowRecord(User user, Book book) {
        this.user = user;
        this.book = book;
        this.isReturned = false;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void markReturned() {
        this.isReturned = true;
    }

    @Override
    public String toString() {
        return "BorrowRecord [User=" + user.getName() + ", Book=" + book.getTitle() + ", Returned=" + isReturned + "]";
    }
}
