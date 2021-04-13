package com.demo.Library.Service;


import com.demo.Library.Controller.dto.GenreDto;
import com.demo.Library.Exceptions.NotFoundException;
import com.demo.Library.Model.Genre;
import com.demo.Library.Repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public void save(GenreDto genreDto) {

        genreRepository.save(new Genre(genreDto.getId(),genreDto.getType()));
    }
    private List<GenreDto> genreMapper(List<Genre> all) {
        return all.parallelStream().map(g-> new ModelMapper().map(g,GenreDto.class))
                .collect(Collectors.toList());
    }

    public List<GenreDto> list() {
        return genreMapper(genreRepository.findAll());
    }

    public GenreDto getOne(Long id) {
        try {
            Genre genre = genreRepository.getOne(id);
            return new GenreDto(id, genre.getType());
        } catch(Exception e){
            throw new NotFoundException("Genre not found");
        }
    }

    public void delete(Long id) {
        try {
            genreRepository.deleteById(id);
        } catch(Exception e){
            throw new NotFoundException("Genre not found");
        }
    }

    public void update(Long id, GenreDto g) {

            Optional<Genre> genre = genreRepository.findById(id);
            if (genre.isPresent())
                genreRepository.save(new Genre(id,g.getType()));
        else {
            throw new NotFoundException("Author not found");
        }
    }
}
