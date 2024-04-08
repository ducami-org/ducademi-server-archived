package ducami.org.ducademi.domain.book.controller;


import ducami.org.ducademi.domain.book.entity.BookEntity;
import ducami.org.ducademi.domain.book.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookEntity> getAllBook() {
        return bookService.getAllBooks();
    }

    @GetMapping("/search")
    public List<BookEntity> getSearchBookByTitle(@RequestParam String title) {
        return bookService.searchBooksByTitle(title);
    }

    @GetMapping("/category/{category}")
    public List<BookEntity> getBooksByCategory(@PathVariable String category) {
        return bookService.getBooksByCategory(category);
    }
}
