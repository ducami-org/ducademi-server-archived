package ducami.org.ducademi.domain.book.controller;


import ducami.org.ducademi.domain.book.category.BookCategory;
import ducami.org.ducademi.domain.book.dto.BookDTO;
import ducami.org.ducademi.domain.book.entity.BookEntity;
import ducami.org.ducademi.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService BOOK_SERVICE;

    // 모든 교재 조회
    @GetMapping("/all")
    public List<BookDTO> getAllBooks() {
        List<BookEntity> bookEntities = BOOK_SERVICE.getAllBooks();
        return bookEntities.stream()
                .map(BOOK_SERVICE::convertToDTO)
                .collect(Collectors.toList());
    }


    // 카테고리별 교재 조회
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

    // 교재 제목으로 검색
    @GetMapping("/search")
    public List<BookDTO> getSearchBooksByTitle(@RequestParam String title) {
        return BOOK_SERVICE.getSearchBooksByTitle(title);
    }


    // 교재 개발
    @PostMapping("/create")
    public ResponseEntity<BookDTO> postCreateBook(@RequestBody BookDTO bookDTO) {
        String title = bookDTO.getTitle();
        String introduction = bookDTO.getIntroduction();

        // 교재 개발
        BookEntity createdBook = BOOK_SERVICE.postCreateBook(title, introduction);

        // 생성된 교재를 DTO로 변환하여 반환
        BookDTO createdBookDTO = BOOK_SERVICE.convertToDTO(createdBook);
        return new ResponseEntity<>(createdBookDTO, HttpStatus.CREATED);
    }

}
