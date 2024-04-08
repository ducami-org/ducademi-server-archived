package ducami.org.ducademi.domain.book.service;

import ducami.org.ducademi.domain.book.entity.BookEntity;
import ducami.org.ducademi.domain.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository BookRepository;

    public BookService(BookRepository BookRepository) {
        this.BookRepository = BookRepository;
    }

    public List<BookEntity> getAllBooks() {
        return BookRepository.findAll();
    }

    public List<BookEntity> getBooksByCategory(String category) {
        return BookRepository.findByCategory(category);
    }

    public List<BookEntity> searchBooksByTitle(String title) {
        return BookRepository.findByTitleContaining(title);
    }

}
