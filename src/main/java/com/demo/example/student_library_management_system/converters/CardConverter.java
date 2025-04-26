package com.demo.example.student_library_management_system.converters;

import com.demo.example.student_library_management_system.model.Card;
import com.demo.example.student_library_management_system.requestdto.CardRequestDto;

public class CardConverter {
    public static Card convertCardRequestDtoRequestIntoCard(CardRequestDto cardRequestDto){
        Card card = new Card();
        card.setCardStatus(cardRequestDto.getCardStatus());
        return card;
    }
}
