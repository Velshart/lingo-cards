package me.mmtr.lingocards.service;

import jakarta.transaction.Transactional;
import me.mmtr.lingocards.data.CardSet;
import me.mmtr.lingocards.repository.dao.interfaces.ICardSetDAO;
import me.mmtr.lingocards.service.interfaces.CardSetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CardSetServiceImpl implements CardSetService {

    private final ICardSetDAO cardSetDAO;

    public CardSetServiceImpl(ICardSetDAO cardSetDAO) {
        this.cardSetDAO = cardSetDAO;
    }

    @Override
    public void saveOrUpdate(CardSet cardSet) {
        cardSetDAO.saveOrUpdate(cardSet);
    }

    @Override
    public Optional<CardSet> getById(Long id) {
        return cardSetDAO.getById(id);
    }

    @Override
    public List<CardSet> getAll() {
        return cardSetDAO.getAll();
    }

    @Override
    public void delete(Long id) {
        cardSetDAO.delete(id);
    }
}
