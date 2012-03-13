package com.dandonov.mongodb.service;

import java.util.Iterator;
import java.util.List;

import com.dandonov.mongodb.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    MongoTemplate mongoTemplate;

    public void logAllPersons() {
        List<User> results = mongoTemplate.findAll(User.class);
        logger.info("Total amount of persons: {}", results.size());
        logger.info("Results: {}", results);
    }

    /**
     * Calculates the average age of a {@link User}.
     *
     * @return the average age
     */
    public int getAvarageAgeOfPerson() {
        List<User> results = mongoTemplate.findAll(User.class);
        int age = 0;
        Iterator<User> personIterator = results.iterator();
        while (personIterator.hasNext()) {
            User nextUser = personIterator.next();
            age += nextUser.getAge();
        }
        return age / results.size();
    }

    public void insertPersonWithNameJohnAndRandomAge() {
        //get random age between 1 and 100
        double age = Math.ceil(Math.random() * 100);

        User p = new User("John", (int) age);

        mongoTemplate.insert(p);
    }

    /**
     * Create a {@link User} collection if the collection does not already exists
     */
    public void createPersonCollection() {
        if (!mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.createCollection(User.class);
        }
    }

    /**
     * Drops the {@link User} collection if the collection does already exists
     */
    public void dropPersonCollection() {
        if (mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.dropCollection(User.class);
        }
    }
}
