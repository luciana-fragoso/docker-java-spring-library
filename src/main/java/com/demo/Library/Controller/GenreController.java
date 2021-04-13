package com.demo.Library.Controller;


import com.demo.Library.Controller.dto.GenreDto;
import com.demo.Library.Service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PutMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public void newGenre(@RequestBody GenreDto genreDto){
        genreService.save(genreDto);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<GenreDto>> list(){
        return ResponseEntity.ok(genreService.list());
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GenreDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(genreService.getOne(id));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateGenre(@PathVariable("id") Long id,@RequestBody GenreDto g){
        genreService.update(id,g);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        genreService.delete(id);
    }


}
