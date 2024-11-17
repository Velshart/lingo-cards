package me.mmtr.lingocards.service.interfaces;

import me.mmtr.lingocards.data.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {
    void saveOrUpdate(Card card);
    Optional<Card> getById(Long id);
    List<Card> getAll();
    void delete(Long id);
}
