package com.midea.junit.test.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.midea.junit.test.domain.Constant.Key;
import com.midea.junit.test.domain.User;

@Repository
public class UserRepository {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(User user) {
		this.mongoTemplate.save(user);
	}

	public void delete(String id) {
		Criteria c = Criteria.where(Key.ID).is(id);
		Query q = new Query(c);
		logger.info("delete q:{}", q);
		this.mongoTemplate.remove(q, User.class);
	}

	public User findUserById(String id) {
		Criteria c = Criteria.where(Key.ID).is(id);
		Query q = new Query(c);
		logger.debug("findById q:{}", q);
		return this.mongoTemplate.findOne(q, User.class);
	}

	public List<User> findUserByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return mongoTemplate.find(query, User.class);
	}
}
