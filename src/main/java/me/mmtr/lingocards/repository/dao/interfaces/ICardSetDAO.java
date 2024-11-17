package me.mmtr.lingocards.repository.dao.interfaces;

import me.mmtr.lingocards.data.Card;
import me.mmtr.lingocards.data.CardSet;

import java.util.List;
import java.util.Optional;

public interface ICardSetDAO {
    Optional<CardSet> getById(Long id);
    List<CardSet> getAll();
    void saveOrUpdate(CardSet cardSet);
    void delete(Long id);
}
