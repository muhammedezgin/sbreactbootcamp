package com.example.sbreactbootcamp.books.controllers;

import com.example.sbreactbootcamp.books.model.Books;
import com.example.sbreactbootcamp.books.repositories.IBooksRepository;
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
@RequestMapping("/api/books")
@Tag(name = "default")
@CrossOrigin("*")
public class BooksController {

    private static final Logger logger= LoggerFactory.getLogger(BooksController.class);

    @Autowired
    IBooksRepository booksRepository;
// aDD
    @Operation(summary = "Creates a new book")
    @PostMapping("/addBook")
    public R<Books> addBook(@RequestBody Books books) {
        try {
            booksRepository.save(books);
        } catch (Exception exception) {
            logger.error("Creates a new books fails" + exception.getMessage());
        }
        return new R<Books>().success();
    }

//find
    @Operation(summary ="Find the Book list")
    @GetMapping("")
    @ResponseBody
    public R<List<Books>> findBooks(){
        List<Books> booksList = null;

        try{
            booksList = booksRepository.findAll();
        }catch (Exception e){
            logger.error("Find the Book list fails: " +e.getMessage());
        }

        return new R<List<Books>>().success().data(booksList);
    }
    //findbyıd
    @Operation(summary = "Retrieve an existing book")
    @GetMapping("/{id}")
    public R<Books> findBook(@Parameter(description = "A book") @PathVariable int id){
        Books books = null;
        try{
            books = booksRepository.findById(books.getId()).orElse(new Books());
        }catch (Exception exception){
            logger.error("Retrieve an existing book fails." + exception.getMessage());
        }
        return new R<Books>().success().data(books);
    }
    //update
    @Operation(summary = "Update an existing a new book")
    @PutMapping
    public R<Books> updateBook(@Parameter(description = "Update an existing book") @RequestBody Books books) {
        try {
            booksRepository.save(books);
        } catch (Exception exception) {
            logger.error("Update an existing book fails:" + exception.getMessage());
        }
        return new R<Books>().success();
    }
    //delete
    @Operation(summary = "Delete an existing book")
    @DeleteMapping(value= "/{id}")
    public R<Books> deleteBook(@Parameter(description = "Delete an existing book.") @PathVariable final int id){
        Books book = null;
        try{
            booksRepository.deleteById(book.getId());
        }catch (Exception exception){
            logger.error("Delete an existing book fails." + exception.getMessage());
        }
        return new R<Books>().success().data(book);
    }


}
