package ducami.org.ducademi.domain.book.repository;

import ducami.org.ducademi.domain.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByCreationDateOderOrderByCreationDateDesc(LocalDate creationDate);
    List<BookEntity> findByCategory(String category);
    List<BookEntity> findByTitleContaining(String title);
}