package com.example.sbreactbootcamp.books.controllers;


import com.example.sbreactbootcamp.books.model.Authors;
import com.example.sbreactbootcamp.books.repositories.IAuthorsRepository;
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
@RequestMapping("/api/authors")
@Tag(name = "default")
@CrossOrigin("*")
public class AuthorsController {
    private static final Logger logger= LoggerFactory.getLogger(AuthorsController.class);

    @Autowired
    IAuthorsRepository authorsRepository;


// ADD
    @Operation(summary = "Create a new authors")
    @PostMapping("/addAuthors")
    public R<Authors> addAuthor(@RequestBody Authors author){
        try{
            authorsRepository.save(author);
        }catch (Exception exception){
           logger.error("Creates a new authors fails" + exception.getMessage());
       }
       return new R<Authors>().success();
    }

//find
    @Operation(summary ="Find the Author list")
    @GetMapping("")
    @ResponseBody
    public R<List<Authors>> findAuthors(){
        List<Authors> authorsList = null;

        try{
            authorsList = authorsRepository.findAll();
        }catch (Exception e){
            logger.error("Find the Authors list fails: " +e.getMessage());
        }
        return new R<List<Authors>>().success().data(authorsList);
    }
   //Update
    @Operation(summary = "Update an existing authors")
   @PutMapping
    public R<Authors> updateAuthors(@Parameter(description="Update an existing authors.") @RequestBody Authors authors){
       try{
            authorsRepository.save(authors);
       }catch (Exception e){
           logger.error("Update an existing authors fails:" +e.getMessage());
        }
        return new R<Authors>().success();
    }

//delete
    @Operation(summary = "Delete authors")
   @DeleteMapping(value= "/{id}")
    public R<Authors> deleteAuthor(@Parameter(description = "Delete an existing authors.") @PathVariable final int Id){
        Authors author= null;
        try{
            authorsRepository.deleteById(author.getId());
        }catch (Exception exception){
            logger.error("Delete an existing author fails." + exception.getMessage());
        }
        return new R<Authors>().success().data(author);
   }
   //finbyıd
    @Operation(summary = "Retrieve an existing authors")
    @GetMapping("/{authors}")
    public R<Authors> findAuthors(@Parameter(description = "A authors") @PathVariable int id) {
        Authors author = null;
        try {
            author = authorsRepository.findById(author.getId()).orElse(new Authors());
        } catch (Exception exception) {
            logger.error("Retrieve an existing authors fails." + exception.getMessage());
        }
        return new R<Authors>().success().data(author);
    }

}
