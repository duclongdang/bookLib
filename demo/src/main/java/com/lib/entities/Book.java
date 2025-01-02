package com.lib.entities;

import java.time.YearMonth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    public String book_title;
    public String genres;
    public YearMonth published_time;
    public Long number_of_copy;

    @Override
    public String toString() {
        return "year month " + YearMonth.now().toString();
    }

    public static void main(String[] args) {
        Book a = new Book();
        System.out.println(a.toString());
    }
}
