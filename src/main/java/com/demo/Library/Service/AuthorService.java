package com.demo.Library.Service;

import com.demo.Library.Controller.dto.AuthorDto;
import com.demo.Library.Exceptions.NotFoundException;
import com.demo.Library.Model.Author;
import com.demo.Library.Repository.AuthorRepository;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void save(AuthorDto a) {
        authorRepository.save(new Author(a.getId(),a.getName()));
    }

    public void update(Long id,AuthorDto a) {

        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent())
            authorRepository.save(new Author(id,a.getName()));
        else{
            throw new NotFoundException("Author not found");
        }
    }

    private List<AuthorDto> authorMapper(List<Author> all) {
        return all.parallelStream().map(a-> new ModelMapper().map(a,AuthorDto.class))
                .collect(Collectors.toList());
    }

    public List<AuthorDto> findAll() {
        return authorMapper(authorRepository.findAll());
    }

    public AuthorDto getOne(Long id){
        try {
            Author author = authorRepository.getOne(id);
            return new AuthorDto(id,author.getName());
        } catch(Exception e) {
            throw new NotFoundException("Author not found");
        }

    }


    public void delete(Long id) {
        try {
            authorRepository.deleteById(id);
        } catch(Exception e){
            throw new NotFoundException("Author not found");
        }
    }
}
