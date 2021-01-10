package com.twitter.twitterback.repository;

import com.twitter.twitterback.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


//todo autocommit transaction
//todo test all methods
//todo checks for not update or delete whole tables
@Repository
public class UserRepository implements CrudRepository<User,Long> {
    //todo change configurations from xml to class
    //todo make this variable autowired

    @Autowired
    private SessionFactory sessionFactory;

    //todo start transactions automatically
    //todo session.close automatically
    @NotNull
    @Override
    public <S extends User> S save(@NotNull S entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(entity);
        session.close();
        return entity;
    }

    @NotNull
    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (S entity :entities) {
            session.save(entity);
        }
        session.close();
        return entities;
    }

    @NotNull
    @Override
    public Optional<User> findById(@NotNull Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class,aLong);
        session.close();
        if (user != null){
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
    @NotNull
    public Optional<User> findByUserName(@NotNull String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = (User) session.createQuery("select s from users s where s.username = '" + userName+"'").getSingleResult();
        session.close();
        if (user != null){
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(@NotNull Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class,aLong);
        session.close();
        return user != null;
    }

    public boolean existsByUserName(@NotNull String userName) {
        return findByUserName(userName).isPresent();
    }

    @NotNull
    @Override
    public Iterable<User> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = (List<User>) session.createQuery("select s from users s").list();
        session.close();
        return users;
    }

    @NotNull
    @Override
    public Iterable<User> findAllById(@NotNull Iterable<Long> longs) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        //todo move inCondition into function
        String inCondition = StreamSupport.stream(longs.spliterator(), true)
                                    .map(Object::toString)
                                    .collect(Collectors.joining("','"));

        List<User> users = (List<User>) session.createQuery("select s from users s where s.id in ('" + inCondition + "')").list();
        session.close();
        return users;
    }


    public void updateUser(User user){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(user);
    }

    @Override
    public long count() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("select count(*) from users s");
        long count = query.getFirstResult();
        session.close();
        return count;
    }

    @Override
    public void deleteById(@NotNull Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(session.get(User.class,aLong));
        session.close();
    }

    @Override
    public void delete(@NotNull User entity) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(entity);
        session.close();
    }

    @Override
    public void deleteAll(@NotNull Iterable<? extends User> entities) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        for(User entity : entities){
            session.delete(entity);
        }
        session.close();
    }

    @Override
    public void deleteAll() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from users").executeUpdate();
        session.close();
    }
}
