package com.dandonov.mongodb;

import com.dandonov.mongodb.service.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main( String[] args ) {
		logger.info("Bootstrapping MongoDemo application");

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");

        UserRepository userRepository = context.getBean(UserRepository.class);

        // cleanup person collection before insertion
        userRepository.dropPersonCollection();

        //create person collection
        userRepository.createPersonCollection();

        for(int i=0; i<20; i++) {
            userRepository.insertPersonWithNameJohnAndRandomAge();
        }

        userRepository.logAllPersons();
        logger.info("Avarage age of a person is: {}", userRepository.getAvarageAgeOfPerson());

        logger.info("Finished MongoDemo application");
	}
}
