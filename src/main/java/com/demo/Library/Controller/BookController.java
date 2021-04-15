package com.demo.Library.Controller;


import com.demo.Library.Controller.dto.BookDto;
import com.demo.Library.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/api/library/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PutMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public void newBook(@RequestBody BookDto bookDto) {
        bookService.save(bookDto);
    }


    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable("id") Long id,@RequestBody BookDto bookDto) {
        bookService.updateBook(id,bookDto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<BookDto>> list(){
        return ResponseEntity.ok(bookService.findAll());
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<BookDto> findBook(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookService.findById(id));
    }


    @GetMapping("/listByTitle")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDto>> findByTitle(@RequestParam("title") String title){
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable("id") Long id){
        bookService.delete(id);
    }



    @GetMapping("/listByGenre")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDto>> findByGenre(@RequestParam("genre_id") int genreId){
        return ResponseEntity.ok(bookService.findByGenre(genreId));
    }


    @GetMapping("/listByAuthor")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDto>> findByAuthor(@RequestParam("author") String authorName){
        return ResponseEntity.ok(bookService.findByAuthor(authorName));
    }





}





