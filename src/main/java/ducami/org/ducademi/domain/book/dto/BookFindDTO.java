package ducami.org.ducademi.domain.book.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BookFindDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String introduction;

    @NotBlank
    private String creationDate;

}
