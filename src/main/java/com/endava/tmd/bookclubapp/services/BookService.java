package com.endava.tmd.bookclubapp.services;

import com.endava.tmd.bookclubapp.entity.Book;
import com.endava.tmd.bookclubapp.entity.Users;
import com.endava.tmd.bookclubapp.repositories.BookOwnerRepository;
import com.endava.tmd.bookclubapp.repositories.BookRepository;
import com.endava.tmd.bookclubapp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Cacheable;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookOwnerRepository bookOwnerRepository;

    @Autowired
    private UsersRepository usersRepository;

    public List<Book> getAll() {

        return bookRepository.findAll();
    }

    public Optional<Book> getByIdBooks(Long id_books) {
        return bookRepository.findById(id_books);
    }

    public void deleteBook(Long id_books) {

        bookRepository.deleteById(id_books);
    }

    public void updateBook(Book book) {
        if (bookRepository.findById(book.getId_books()).isPresent())
            bookRepository.save(book);
    }
    public void addBook(Book book) {
        Book checkBook= bookRepository.findBookByIdbookOrAndTitleOrAndAuthor(book.getId_books(),book.getTitle(),book.getAuthor(),book.getDescription(),book.getNumberOfCopies());
        if (checkBook==null){
            bookRepository.save(book);
        }
    }



    public List<Book> fetchBooks(String searchTerm) throws Exception {
        return bookRepository.fetch(searchTerm);

    }
}
