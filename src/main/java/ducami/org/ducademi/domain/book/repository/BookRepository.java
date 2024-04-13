package ducami.org.ducademi.domain.book.repository;


import ducami.org.ducademi.domain.book.category.BookCategory;
import ducami.org.ducademi.domain.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    // 모든 교재 조회
    List<BookEntity> findAllBooks();

    // 카테고리별 교재 조회
    List<BookEntity> findByCategory(BookCategory category);

    // 교재 제목으로 조회
    List<BookEntity> findByTitle(String title);

}