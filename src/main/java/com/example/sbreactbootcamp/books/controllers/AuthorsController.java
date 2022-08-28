package com.example.sbreactbootcamp.books.controllers;


import com.example.sbreactbootcamp.books.model.Authors;
import com.example.sbreactbootcamp.books.model.Books;
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
    public R<Authors> updateAuthors(@RequestBody Authors author){
       try{
            authorsRepository.save(author);
       }catch (Exception e){
           logger.error("Update an existing authors fails:" +e.getMessage());
        }
        return new R<Authors>().success();
    }

//delete
@DeleteMapping("/{id}")
@ResponseBody
public R<Authors> deleteAuthor(@PathVariable String id)
{
    try {
        authorsRepository.deleteById(id);
    }catch (Exception e)
    {
        logger.error(e.getMessage());
    }
    return  new  R<Authors>().success();
}

   //finbyıd
   @GetMapping("/{id}")
   @ResponseBody
   public R<Authors> findBookById(@PathVariable String id)
   {
       Authors authors =null;

       try {
           authors =authorsRepository.findById(id).orElse(new Authors());
       }catch (Exception e)
       {
           logger.error(e.getMessage());
       }
       return  new  R<Authors>().success().data(authors);
   }

}
