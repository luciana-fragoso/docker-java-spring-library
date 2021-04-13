package com.demo.Library.Controller.dto;

import com.demo.Library.Model.Author;
import com.demo.Library.Model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private List<Author> author;
    private Genre genre;
}
