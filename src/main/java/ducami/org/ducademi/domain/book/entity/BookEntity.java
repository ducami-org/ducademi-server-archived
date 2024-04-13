package ducami.org.ducademi.domain.book.entity;

import ducami.org.ducademi.domain.book.category.BookCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "book")
@NoArgsConstructor
public class BookEntity {

    // 교재 PK
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookIdx;

    // 교재 저자
    @Column(name = "author", nullable = false)
    private Long authorIdx;

    // 교재 제목
    @Column(name = "title", nullable = false)
    private String title;

    // 교재 생성일
    @Column(name = "crearedDate", nullable = false)
    private LocalDate createdDate;

    // 교재 표지
    @Column(name = "bookCover", nullable = false, unique = true)
    private String bookCover;

    // 교재 소개글
    @Column(name = "introduction", nullable = false)
    private String introduction;

    // 교재 카테고리
    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookCategory category;

    // 교재 수정일
    @Column(name = "modifiedDate", nullable = false)
    private LocalDate modifiedDate;
}
