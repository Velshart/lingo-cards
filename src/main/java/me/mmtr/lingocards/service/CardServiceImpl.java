package me.mmtr.lingocards.service;

import jakarta.transaction.Transactional;
import me.mmtr.lingocards.data.Card;
import me.mmtr.lingocards.repository.dao.interfaces.ICardDAO;
import me.mmtr.lingocards.service.interfaces.CardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    private final ICardDAO cardDAO;

    public CardServiceImpl(ICardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    @Override
    public void saveOrUpdate(Card card) {
        cardDAO.saveOrUpdate(card);
    }

    @Override
    public Optional<Card> getById(Long id) {
       return cardDAO.getById(id);
    }

    @Override
    public List<Card> getAll() {
        return cardDAO.getAll();
    }

    @Override
    public void delete(Long id) {
        cardDAO.delete(id);
    }
}
