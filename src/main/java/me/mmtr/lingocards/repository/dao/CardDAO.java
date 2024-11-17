package me.mmtr.lingocards.repository.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import me.mmtr.lingocards.data.Card;
import me.mmtr.lingocards.repository.dao.interfaces.ICardDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CardDAO implements ICardDAO {

    @PersistenceContext
    private EntityManager entityManager;
    private final String GET_ALL_JPQL = "FROM me.mmtr.lingocards.data.Card";
    private final String GET_BY_ID_JPQL = "SELECT c FROM me.mmtr.lingocards.data.Card c WHERE c.id = :id";

    public CardDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Card> getById(Long id) {
        TypedQuery<Card> query = entityManager.createQuery(GET_BY_ID_JPQL, Card.class);
        query.setParameter("id", id);

        try {
            return Optional.of(query.getSingleResult());
        }catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Card> getAll() {
        TypedQuery<Card> query = entityManager.createQuery(GET_ALL_JPQL, Card.class);
        return query.getResultList();
    }

    @Override
    public void saveOrUpdate(Card card) {
        if (getById(card.getId()).isEmpty()) {
            entityManager.persist(card);
        }else {
            entityManager.merge(card);
        }
    }

    @Override
    public void delete(Long id) {
        getById(id).ifPresent(entityManager::remove);
    }
}
