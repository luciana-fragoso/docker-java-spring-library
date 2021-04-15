package com.demo.Library.Repository;

import com.demo.Library.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select b.id,b.title, b.genre_id from Book b join Book_Author ba on (b.id = ba.book_id) join Author a on (ba.author_id = a.id) where a.name= :authorName order by (b.id);",nativeQuery = true)
    List<Book> findByAuthor(@Param("authorName") String authorName);
    @Query(value = "select ba.author_id, b.id ,b.title, b.genre_id from Book b join Book_Author ba on (b.id = ba.book_id) where b.genre_id= :id order by (b.genre_id);",nativeQuery = true)
    List<Book> findByGenre(@Param("id") int id);

    List<Book> findByTitle(String title);



}






