package com.demo.Library.Controller;


import com.demo.Library.Controller.dto.AuthorDto;
import com.demo.Library.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PutMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public void newAuthor(@RequestBody AuthorDto authorDto){
        authorService.save(authorDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editAuthor(@PathVariable("id") Long id,@RequestBody AuthorDto a){
        authorService.update(id,a);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AuthorDto>> list(){
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDto> find(@PathVariable Long id){
        return ResponseEntity.ok(authorService.getOne(id));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        authorService.delete(id);
    }



}
