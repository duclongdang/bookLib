package com.lib.demo.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.lib.demo.dto.AuthorDto;
import com.lib.demo.dto.BookDto;
import com.lib.demo.response.ResponseData;
import com.lib.demo.services.AuthorService;
import com.lib.demo.services.BookService;
import com.lib.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @GetMapping("/book")
    public ResponseEntity<Object> getBooks(@RequestParam(name = "searchTitle") String searchTitle) {
        String url = "https://openlibrary.org/search.json?title=" + searchTitle + "&mode=ebook";

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

        JsonNode node = response.getBody();
        if (node != null) {
            List<BookDto> books = new ArrayList<>();

            JsonNode docs = node.get("docs");
            for (JsonNode doc : docs) {
                BookDto book = new BookDto();

                String title = doc.get("title").asText();
                book.setBookTitle(title);

                Set<String> authors = new HashSet<>();
                if (doc.has("author_name") && doc.get("author_name").isArray()) {
                    for (JsonNode author : doc.get("author_name")) {
                        String authorName = author.asText();
                        authors.add(authorName);
                    }
                    book.setAuthors(authors);
                }
                String first_publish_year = doc.path("first_publish_year").asText(null);
                Year year;
                if (first_publish_year != null && !first_publish_year.isEmpty()) {
                    try{
                        year = Year.parse(first_publish_year, DateTimeFormatter.ofPattern("yyyy"));
                        book.setPublishedTime(year);
                    }catch(DateTimeParseException e){
                        System.out.println("Không thể chuyển đổi ngày xuaats bản");
                    }
                }
                books.add(book);
            }
            for (BookDto book : books) {
                if (!bookService.existsBookByTitle(book.getBookTitle())) {
                    bookService.addBook(book);
                } else {
                    bookService.editBook(book);
                }
            }
            return new ResponseEntity<>(new ResponseData<>(response.getBody(), HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value()), HttpStatus.OK);

        } else
            return new ResponseEntity<>(new ResponseData<>(null, HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);

    }

    @GetMapping("/author")
    public ResponseEntity<Object> getAuthors(@RequestParam(name = "authorName") String authorName) {
        String url = "https://openlibrary.org/search/authors.json?q=" + authorName;
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode node = response.getBody();
        if (node != null) {
            List<AuthorDto> authors = new ArrayList<>();
            JsonNode docs = node.path("docs");

            for (JsonNode author : docs) {

                AuthorDto authorDto = new AuthorDto();
                if (!authorService.existsByAuthorName(author.path("name").asText())) {
                    authorDto.setAuthorName(author.path("name").asText());
                    authorService.addAuthor(authorDto);
                }
            }

        }
        return new ResponseEntity<>(new ResponseData<>(response.getBody(), HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value()), HttpStatus.OK);
    }
}
