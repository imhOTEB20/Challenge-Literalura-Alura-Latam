package com.challenge.literalura;

import com.challenge.literalura.model.consumption.ResponseData;
import com.challenge.literalura.model.persists.Author;
import com.challenge.literalura.model.persists.Book;

import com.challenge.literalura.service.consumption.APIConsumption;
import com.challenge.literalura.service.consumption.ConvertData;
import com.challenge.literalura.service.persists.AuthorService;
import com.challenge.literalura.service.persists.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class App{
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    private Scanner scanner = new Scanner(System.in);
    private int mainOption;

    public void start() {
        do {
            printMainMenu();
            mainOption = scanner.nextInt();
            scanner.nextLine();

            switch (mainOption) {
                case 1:
                    caseOne();
                    break;
                case 2:
                    caseTwo();
                    break;
                case 3:
                    printTopMostDownloaded();
                    break;
                case 4:
                    printAllBookOnRegister();
                    break;
                case 5:
                    printAllAuthorsOnRegister();
                    break;
                case 6:
                    printAllAuthorAliveInYear();
                    break;
                case 7:
                    printAllBookFromLanguage();
                    break;
                case 0:
                    System.out.println("Muchas gracias. Adios.");
                    break;
                default:
                    System.out.println("Opción no valida");
                    break;
            }
        } while (mainOption != 0);
    }

    public void caseOne() {
        String url = "https://gutendex.com/books/?search=" + enterTitle().replace(" ", "%20");
        String json = APIConsumption.obtenerDatos(url);

        var convertData = new ConvertData();

        var responseData = convertData.getData(json, ResponseData.class);

        if(!responseData.results().isEmpty()) {
            var searchedBook = new Book(responseData.results().get(0));
            this.bookService.saveBook(searchedBook);
            System.out.println("Libro encontrado:");
            System.out.println(searchedBook);
        }
        //else lanzar error "No se encontraron titulos que coincidan con %s".formatted(title);
    }

    public void caseTwo() {
        String title = enterTitle();
        Optional<Book> bookOnRegister = bookService.findBookByTitleOnRegister(title);
        if (bookOnRegister.isPresent()) {
            System.out.println("Libro encontrado:");
            System.out.println(bookOnRegister.get());
        } else {
            System.out.printf("No se encontro ningún libro que coincida con %s.%n",title);
        }
    }
    private String enterTitle() {
        String title;
        System.out.println("Ingresa el titulo del libro o parte del mismo:");
        title = scanner.nextLine();
        return title;
    }

    public void printMainMenu() {
        System.out.println("""
                SELECCIONA UNA OPCION:
                1. Agregar un libro nuevo al registro.
                2. Buscar un libro del registro.
                3. TOP de libros más descargados del registro.
                4. Listar libros registrados.
                5. Listar autores registrados.
                6. Listar autores vivos en un determinado año.
                7. Listar libros por idioma.
                0. SALIR""");
    }

    public void printTopMostDownloaded() {
        List<Book> top5Book = bookService.findTop5ByDownloadCount();
        System.out.println(top5Book.size());
        if(top5Book.size() == 5) {
            System.out.println("TOP 5 LIBROS MAS DESCARGADOS:");
        }
        else if (!top5Book.isEmpty()) {
            System.out.println("Hay menos de 5 libros registrados.");
            System.out.printf("TOP %d LIBROS MAS DESCARGADOS:%n", top5Book.size());
        }
        else {
            System.out.println("No hay libros registrados.");
        }
    }

    private void printAllBookFromLanguage() {
        System.out.println("Ingresa un idioma en código ISO 639-1 (en,es,fr,de,it,ja,zh, etc):");
        String languageType = scanner.nextLine();

        List<Book> booksFromLanguages = bookService.booksFromLanguage(languageType.toLowerCase());
        if (!booksFromLanguages.isEmpty()) {
            System.out.printf("Libros con el lenguaje %s. %n", languageType);
            booksFromLanguages.forEach(System.out::println);
        } else
            System.out.printf("NO HAY LIBROS REGISTRADOS CON EL IDIOMA %s. %n", languageType.toUpperCase());
    }

    private void printAllAuthorAliveInYear() {
        System.out.println("Ingresa un año de para verificar que autores estaban vivo:");
        Integer year = scanner.nextInt();
        scanner.nextLine();

        List<Author> authoresAlive = authorService.findAuthorsAliveInYear(year);
        if (!authoresAlive.isEmpty()) {
            System.out.printf("Autores vivos en el año %d: %n", year);
            authoresAlive.forEach(System.out::println);
        } else System.out.printf("NO HAY AUTORES REGISTRADOS QUE HAYAN ESTADO VIVIO EN EL AÑO %d. %n", year);
    }

    private void printAllAuthorsOnRegister() {
        List<Author> authors = authorService.allAuthorOnRegister();
        if (!authors.isEmpty())
            authors.forEach(System.out::println);
        else System.out.println("NO HAY AUTORES REGISTRADOS.");
    }

    private void printAllBookOnRegister() {
        List<Book> books = bookService.allBookOnRegister();
        if(!books.isEmpty())
            books.forEach(System.out::println);
        else System.out.println("NO HAY LIBROS REGISTRADOS.");
    }
}