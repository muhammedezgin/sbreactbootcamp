package com.example.sbreactbootcamp.books.controllers;

import com.example.sbreactbootcamp.books.model.Books;
import com.example.sbreactbootcamp.books.repositories.IBooksRepository;
import com.example.sbreactbootcamp.response.R;
import com.example.sbreactbootcamp.users.controllers.UserController;
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
@Tag(name="default")
@CrossOrigin("*")
public class BooksController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    IBooksRepository booksRepository;


    @Operation(summary = "Find the book list")
    @GetMapping("")
    @ResponseBody
    public R<List<Books>> findBooks()
    {
        List<Books> booksList = null;

        try {
            booksList=booksRepository.findAll();
        }catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return  new R<List<Books>>().success().data(booksList);

    }
    @GetMapping("/{id}")
    @ResponseBody
    public R<Books> findBookById(@PathVariable String id)
    {
        Books book =null;

        try {
            book =booksRepository.findById(id).orElse(new Books());
        }catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return  new  R<Books>().success().data(book);
    }
    @Operation(summary = "Update an existing  Book")
    @PutMapping
    public R<Books> updateBook(@RequestBody Books book)
    {
        try {
            booksRepository.save(book);
        }catch (Exception e){
            logger.error(e.getMessage());
        }


        return new R<Books>().success();
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Books> deleteBook(@PathVariable String id)
    {
        try {
            booksRepository.deleteById(id);
        }catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return  new  R<Books>().success();
    }

}
