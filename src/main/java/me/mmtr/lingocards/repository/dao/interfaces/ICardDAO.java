package me.mmtr.lingocards.repository.dao.interfaces;

import me.mmtr.lingocards.data.Card;

import java.util.List;
import java.util.Optional;

public interface ICardDAO {
    Optional<Card> getById(Long id);
    List<Card> getAll();
    void saveOrUpdate(Card card);
    void delete(Long id);
}
