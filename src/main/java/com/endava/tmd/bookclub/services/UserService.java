package com.endava.tmd.bookclub.services;

import com.endava.tmd.bookclub.entity.Book;
import com.endava.tmd.bookclub.entity.BookOwner;
import com.endava.tmd.bookclub.entity.User;
import com.endava.tmd.bookclub.repositories.BookOwnerRepository;
import com.endava.tmd.bookclub.repositories.BookRepository;
import com.endava.tmd.bookclub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookOwnerRepository bookOwnerRepository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {

        return repository.findAll();
    }

    public Optional<User> getbyid(long id) {

        return repository.findById(id);
    }

    public void addUser(User user) {
        User checkUser=repository.findUserByFirst_nameOrLast_nameOrEmail(user.getFirst_name(), user.getLast_name(), user.getEmail());
        if(checkUser==null)
             repository.save(user);
    }

    public void deleteUser(Long id) {

        repository.deleteById(id);
    }

    public void updateUser(User user) {
        if (repository.findById(user.getId()).isPresent())
            repository.save(user);
    }
    public Optional<User> getById(Long id)
    {

        return repository.findById(id);
    }

    public void addBookOwner(Long iduser, BookOwner bookOwner, Book book)  {
        User tempUser = repository.findById(iduser).get();
        Book tempBook= bookRepository.findBookByTitle(book.getTitle());
        BookOwner tempBookOwner = (BookOwner) bookOwnerRepository.findByBookOwnerId(bookOwner.getId_book_owner());
        if(tempBookOwner == null)
        {
            tempBookOwner = bookOwnerRepository.save(bookOwner);
        }
        BookOwner bookOwner1 = new BookOwner();
        bookOwner1.setUser(tempUser);
        bookOwner1.setBook(tempBook);
        bookOwnerRepository.save(bookOwner1);
    }

    public String getBooksReturnToOwner(Long iduser)
    {
        StringBuilder result = new StringBuilder();
        List<Book> books = repository.findbooksListbyId(iduser);
        books = books.stream()
                .filter(book -> book.getRentedBy().stream().anyMatch(rent -> loan.getEndDate().compareTo(LocalDate.now())>=0))
                .collect(Collectors.toList());
        for (BookOwner bookOwner : bookOwner) {
            result.append("Book with id = ")
                    .append(bookOwner.getId()).append(", Title = ")
                    .append(bookOwner().getTitle())
                    .append(" will be returned at ")
                    .append(bookOwner.getRentedBy().get(bookOwner.getRentedBy().size() - 1).getEndDate()).append(" by ")
                    .append(bookOwner.getRentedBy().get(bookOwner.getRentedBy().size() - 1).getUser().getName()).append("\n");
        }
        return result.toString();
    }
}
