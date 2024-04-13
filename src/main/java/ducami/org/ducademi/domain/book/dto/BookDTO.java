package ducami.org.ducademi.domain.book.dto;

import ducami.org.ducademi.domain.book.category.BookCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {
    private Long bookIdx;
    private String title;
    private LocalDate createdDate;
    private String bookCover;
    private String introduction;
    private BookCategory category;
    private LocalDate modifiedDate;
}
