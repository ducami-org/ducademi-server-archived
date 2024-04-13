package ducami.org.ducademi.domain.book.controller;


import ducami.org.ducademi.domain.book.category.BookCategory;
import ducami.org.ducademi.domain.book.dto.BookDTO;
import ducami.org.ducademi.domain.book.entity.BookEntity;
import ducami.org.ducademi.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService BOOK_SERVICE;

    @GetMapping("/all")
    public List<BookDTO> getAllBooks() {
        List<BookEntity> bookEntities = BOOK_SERVICE.getAllBooks();
        return bookEntities.stream()
                .map(BOOK_SERVICE::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/category/{category}")
    public List<BookDTO> getBooksByCategory(@PathVariable String category) {
        BookCategory bookCategory = null;
        for (BookCategory cat : BookCategory.values()) {
            if (cat.name().equalsIgnoreCase(category)) {
                bookCategory = cat;
                break;
            }
        }
        if (bookCategory == null) {
            throw new IllegalArgumentException("존재하지 않는 카테고리: " + category);
        }
        return BOOK_SERVICE.getBooksByCategory(bookCategory);
    }

    @GetMapping("/search")
    public List<BookDTO> getSearchBooksByTitle(@RequestParam String title) {
        return BOOK_SERVICE.searchBooksByTitle(title);
    }

}
