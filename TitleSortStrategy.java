import java.util.Comparator;
import java.util.List;
interface SortStrategy {
    List<Book> sort(List<Book> books);
}

public class TitleSortStrategy implements SortStrategy {
    @Override
    public List<Book> sort(List<Book> books) {
        books.sort(Comparator.comparing(Book::getTitle));
        return books;
    }
}
class AuthorSortStrategy implements SortStrategy {
    @Override
    public List<Book> sort(List<Book> books) {
        books.sort(Comparator.comparing(Book::getAuthor));
        return books;
    }
}
