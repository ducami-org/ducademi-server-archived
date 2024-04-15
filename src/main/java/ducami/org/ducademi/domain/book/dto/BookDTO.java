package ducami.org.ducademi.domain.book.dto;

import ducami.org.ducademi.domain.book.category.BookCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {
    @NotBlank
    private Long bookIdx;

    @NotBlank
    private Long authorIdx;

    @NotBlank
    private String title;

    @NotBlank
    private LocalDate createdDate;

    @NotBlank
    private String bookCover;

    @NotBlank
    private String introduction;

    @NotBlank
    private BookCategory category;

    @NotBlank
    private LocalDate modifiedDate;
}
