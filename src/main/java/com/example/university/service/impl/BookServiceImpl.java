package com.example.university.service.impl;

import com.example.university.entity.Book;
import com.example.university.exceptions.ResourceNotFound;
import com.example.university.payload.BookPayload;
import com.example.university.payload.Result;
import com.example.university.repository.BookRepository;
import com.example.university.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final MyFileService myFileService;
    private final BookCategoryServiceImpl bookCategoryService;
    private final BookRepository bookRepository;

    @Override
    public Result saveBook(BookPayload bookPayload) {
        try {
            Book book = new Book();
            book.setFile(myFileService.findByHashId(bookPayload.getFileHashId()));
            book.setImage(myFileService.findByHashId(bookPayload.getImageHashId()));
            book.setNameUz(bookPayload.getNameUz());
            book.setNameRu(bookPayload.getNameRu());
            book.setNameEn(bookPayload.getNameEn());
            book.setCategory(bookCategoryService.findBookCategoryById(bookPayload.getCategoryId()));

            bookRepository.save(book);

            return Result.success(book);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result editBook(UUID bookId, BookPayload bookPayload) {
        try {
            Book book = bookRepository.findById(bookId).orElseThrow(
                    () -> new ResourceNotFound("book", "id", bookId)
            );
            if (bookPayload.getFileHashId() != null)
                book.setFile(myFileService.findByHashId(bookPayload.getFileHashId()));
            if (bookPayload.getImageHashId() != null)
                book.setImage(myFileService.findByHashId(bookPayload.getImageHashId()));
            if (bookPayload.getNameUz() != null)
                book.setNameUz(bookPayload.getNameUz());
            if (bookPayload.getNameRu() != null)
                book.setNameRu(bookPayload.getNameRu());
            if (bookPayload.getNameEn() != null)
                book.setNameEn(bookPayload.getNameEn());
            if (bookPayload.getCategoryId() != null)
                book.setCategory(bookCategoryService.findBookCategoryById(bookPayload.getCategoryId()));

            bookRepository.save(book);

            return Result.success(book);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result deleteBook(UUID bookId) {
        try {
            Book book = bookRepository.findById(bookId).orElseThrow(
                    () -> new ResourceNotFound("book", "id", bookId)
            );
            bookRepository.deleteById(bookId);
            new File(String.format("%s/%s.%s", book.getFile().getUploadPath(), book.getFile().getHashId(), book.getFile().getExtension())).delete();
            new File(String.format("%s/%s.%s", book.getImage().getUploadPath(), book.getImage().getHashId(), book.getImage().getExtension())).delete();

            return Result.message("successful deleted", true);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Page<Book> getBooksWithPageable(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> getBooksWithPageableByCategoryId(int pageNo, int pageSize, UUID categoryId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        return bookRepository.findAllByCategory_Id(categoryId, pageable);
    }

    @Override
    public Result findBookById(UUID bookId) {
        try {
            return Result.success(bookRepository.findById(bookId).orElseThrow(
                    () -> new ResourceNotFound("book", "id", bookId)
            ));
        }catch (Exception e){
            return Result.exception(e);
        }
    }

}
