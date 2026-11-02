
package se.yrgo.bokklubben.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.yrgo.bokklubben.domain.Book;

@Controller
public class BookController {

    private final List<Book> books = List.of(
        new Book("Harry Potter", "J.K. Rowling", "Fantasy"),
        new Book("Hobbiten", "J.R.R. Tolkien", "Fantasy"),
        new Book("Män som hatar kvinnor", "Stieg Larsson", "Deckare"),
        new Book("Mordet på Orientexpressen", "Agatha Christie", "Deckare")
    );

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Välkommen till Bokklubben!");
        model.addAttribute("date", LocalDate.now());
        return "home";
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("books", books);
        return "booklist";
    }

    @GetMapping("/genre")
    public String showGenre(
            @RequestParam(required = false) String type,
            Model model) {

        List<Book> filteredBooks;

        if (type == null || type.isBlank()) {
            filteredBooks = books;
        } else {
            filteredBooks = books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(type))
                .toList();
        }

        model.addAttribute("books", filteredBooks);
        model.addAttribute("selectedGenre", type);

        return "genre";
    }
}
