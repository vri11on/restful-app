package com.example.restfulapp.dao;

import com.example.restfulapp.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

public abstract class AbstractCrudDao<T extends BaseEntity> implements CrudDao<T> {

    protected Class<T> tClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractCrudDao(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Transactional
    @Override
    public T create(T t) {
        entityManager.persist(t);
        entityManager.flush();
        return t;
    }

    @Transactional
    @Override
    public T update(T t) {
        entityManager.merge(t);
        entityManager.flush();
        return t;
    }

    @Transactional
    @Override
    public T get(Long id) {
        return entityManager.find(tClass, id);
    }

    @Transactional
    @Override
    public Boolean delete(T t) {
        return delete(t.getId());
    }

    @Transactional
    @Override
    public Boolean delete(Long id) {
        T toRemove = get(id);
        entityManager.remove(toRemove);
        entityManager.flush();
        return get(toRemove.getId()) == null;
    }

    @Transactional
    @Override
    public Collection<T> getAll(Integer limit) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> rootEntry = cq.from(tClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        allQuery.setMaxResults(limit);
        return allQuery.getResultList();
    }

    public abstract T patchUpdate(T t);
}
