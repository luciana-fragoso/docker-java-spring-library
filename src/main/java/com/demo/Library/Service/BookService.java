package com.demo.Library.Service;



import com.demo.Library.Controller.dto.AuthorDto;
import com.demo.Library.Controller.dto.BookDto;
import com.demo.Library.Exceptions.NotFoundException;
import com.demo.Library.Model.Author;
import com.demo.Library.Model.Book;
import com.demo.Library.Repository.BookRepository;
import jdk.jshell.spi.ExecutionControlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void save(BookDto bookDto) {
       bookRepository.save(new Book(bookDto.getId(),bookDto.getTitle(),bookDto.getAuthor(),bookDto.getGenre()));
    }

    public void delete(Long id){
        try{
            bookRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundException("Book not found");
        }
    }



    private List<BookDto> booksMapper(List<Book> all) {
        return all.parallelStream().map(b-> new ModelMapper().map(b,BookDto.class))
                .collect(Collectors.toList());
    }

    public List<BookDto> findAll(){
        return booksMapper(bookRepository.findAll());
    }



    public List<BookDto> findByTitle(String title){
        return booksMapper(bookRepository.findByTitle(title));
    }

    public BookDto findById(Long id){
        try {
            Book b =  bookRepository.getOne(id);
            return new BookDto(id,b.getTitle(),b.getAuthor(),b.getGenre());
        } catch (Exception e){
            throw new NotFoundException("Book not found");
        }
    }

    public void updateBook(Long id,BookDto bookDto){


            Optional<Book> b = bookRepository.findById(id);
            if (b.isPresent())
                bookRepository.save(new Book(id, bookDto.getTitle(), bookDto.getAuthor(),bookDto.getGenre()));

        else {
            throw new NotFoundException("Book not found");
        }


      //  bookRepository.save(new Book(id,bookDto.getTitle(),bookDto.getAuthor(),bookDto.getGenre()));

    }
    /*
    public List<BookDto> findByGenre(int id) {
        return booksMapper(bookRepository.findByGenre(id));
    }
    public List<BookDto> findByAuthor(String author){
        return booksMapper(bookRepository.findByAuthor(author));
    }
    */

}




