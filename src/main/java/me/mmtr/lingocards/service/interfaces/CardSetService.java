package me.mmtr.lingocards.service.interfaces;

import me.mmtr.lingocards.data.CardSet;

import java.util.List;
import java.util.Optional;

public interface CardSetService {
    void saveOrUpdate(CardSet cardSet);

    Optional<CardSet> getById(Long id);

    List<CardSet> getAll();

    void delete(Long id);
}
