package com.example.sbreactbootcamp.books.controllers;

import com.example.sbreactbootcamp.books.model.Genres;
import com.example.sbreactbootcamp.books.repositories.IGenresRepository;

import com.example.sbreactbootcamp.response.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@Tag(name = "default")
@CrossOrigin("*")
public class GenresController {

    private static final Logger logger= LoggerFactory.getLogger(GenresController.class);

    @Autowired
    IGenresRepository genresRepository;

    @Operation(summary = "Find the generes list")
    @GetMapping("")
    @ResponseBody
    public R<List<Genres>> findGenres(){
        List<Genres> genresList = null;

        try{
            genresList = genresRepository.findAll();
        }catch (Exception e){
            logger.error("Find the genres list fails" + e.getMessage());
        }

        return new R<List<Genres>>().success().data(genresList);
    }
    //ADD
    @Operation(summary = "Create a new genres")
    @PostMapping("/addGenres")
    public R<Genres> addGenres(@RequestBody Genres genres){
        try{
            genresRepository.save(genres);
        }catch (Exception exception){
            logger.error("Creates a new genres fails" + exception.getMessage());
        }
        return new R<Genres>().success();
    }
    //finbyID
    @Operation(summary = "Retrieve an existing genres")
    @GetMapping("/{genres}")
    public R<Genres> findGenre(@Parameter(description = "A genres") @PathVariable int id) {
        Genres genres = null;
        try {
            genres = genresRepository.findById(genres.getId()).orElse(new Genres());
        } catch (Exception exception) {
            logger.error("Retrieve an existing genres fails." + exception.getMessage());
        }
        return new R<Genres>().success().data(genres);
    }
    //Delete
    @Operation(summary = "Delete genres")
    @DeleteMapping(value= "/{id}")
    public R<Genres> deleteGenre(@Parameter(description = "Delete an existing genres.") @PathVariable final int Id){
        Genres genres= null;
        try{
            genresRepository.deleteById(genres.getId());
        }catch (Exception exception){
            logger.error("Delete an existing genres fails." + exception.getMessage());
        }
        return new R<Genres>().success().data(genres);
    }
    //update
    @Operation(summary = "Update an existing a new genres")
    @PutMapping
    public R<Genres> updateGenre(@Parameter(description = "Update an existing genres") @RequestBody Genres genres) {
        try {
            genresRepository.save(genres);
        } catch (Exception exception) {
            logger.error("Update an existing genres fails:" + exception.getMessage());
        }
        return new R<Genres>().success();
    }

}
