package ducami.org.ducademi.domain.book.service;

import ducami.org.ducademi.domain.book.category.BookCategory;
import ducami.org.ducademi.domain.book.dto.BookDTO;
import ducami.org.ducademi.domain.book.entity.BookEntity;
import ducami.org.ducademi.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository BOOK_REPOSITORY;


    // 모든 교재 조회
    public List<BookEntity> getAllBooks() {
        return BOOK_REPOSITORY.findAllBooks();
    }

    // 특정 카테고리의 교재 조회
    public List<BookDTO> getBooksByCategory(BookCategory category) {
        List<BookEntity> bookEntities = BOOK_REPOSITORY.findByCategory(category);
        return bookEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 교재 제목으로 검색
    public List<BookDTO> searchBooksByTitle(String title) {
        List<BookEntity> bookEntities = BOOK_REPOSITORY.findByTitle(title);
        return bookEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public BookDTO convertToDTO(BookEntity bookEntity) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookIdx(bookEntity.getBookIdx());
        bookDTO.setTitle(bookEntity.getTitle());
        bookDTO.setCreatedDate(bookEntity.getCreatedDate());
        bookDTO.setBookCover(bookEntity.getBookCover());
        bookDTO.setIntroduction(bookEntity.getIntroduction());
        bookDTO.setCategory(bookEntity.getCategory());
        bookDTO.setModifiedDate(bookEntity.getModifiedDate());
        return bookDTO;
    }

}
