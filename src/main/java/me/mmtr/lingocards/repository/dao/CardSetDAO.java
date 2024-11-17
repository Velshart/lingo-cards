package me.mmtr.lingocards.repository.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import me.mmtr.lingocards.data.CardSet;
import me.mmtr.lingocards.repository.dao.interfaces.ICardSetDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CardSetDAO implements ICardSetDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<CardSet> getById(Long id) {
        String GET_BY_ID_JPQL = "SELECT cs FROM me.mmtr.lingocards.data.CardSet cs WHERE cs.id = :id";

        TypedQuery<CardSet> query = entityManager.createQuery(GET_BY_ID_JPQL, CardSet.class);
        query.setParameter("id", id);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<CardSet> getAll() {
        String GET_ALL_JPQL = "FROM me.mmtr.lingocards.data.CardSet";

        TypedQuery<CardSet> query = entityManager.createQuery(GET_ALL_JPQL, CardSet.class);
        return query.getResultList();
    }

    @Override
    public void saveOrUpdate(CardSet cardSet) {

        if (getById(cardSet.getId()).isEmpty()) {
            entityManager.persist(cardSet);
        } else {
            entityManager.merge(cardSet);
        }
    }

    @Override
    public void delete(Long id) {
        getById(id).ifPresent(entityManager::remove);
    }
}
