package com.lib.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.time.YearMonth;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long bookId;
    private String bookTitle;
    private Year publishedTime;
    private Long numberOfPages;
    private Set<String> authors;
    private Set<String> genres;
}
