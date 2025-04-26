package com.demo.example.student_library_management_system.services;

import com.demo.example.student_library_management_system.converters.TransactionConverter;
import com.demo.example.student_library_management_system.model.Book;
import com.demo.example.student_library_management_system.model.Card;
import com.demo.example.student_library_management_system.model.Transaction;
import com.demo.example.student_library_management_system.repository.BookRepository;
import com.demo.example.student_library_management_system.repository.CardRepository;
import com.demo.example.student_library_management_system.repository.TransactionRepository;
import com.demo.example.student_library_management_system.requestdto.TransactionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CardRepository cardRepository;
    public String saveTransaction(TransactionRequestDto transactionRequestDto){
        Transaction transaction = TransactionConverter.convertTransactionRequestDtoIntoTransaction(transactionRequestDto);
        Book book = bookRepository.findById(transactionRequestDto.getBookId()).get();
        transaction.setBook(book);
        Card card=cardRepository.findById(transactionRequestDto.getCardId()).get();
        transaction.setCard(card);
        transactionRepository.save(transaction);
        return "transaction saved successfully";
    }
}
