package com.midea.junit.test.job;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.midea.junit.test.domain.Constant.Key;
import com.midea.junit.test.domain.User;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MongoTemplate mongoTemplate;
	protected static final Queue<User> USER_QUEUE = new ArrayBlockingQueue<>(100);

	// 每分钟执行一次
	@Scheduled(cron = "* * * * * ?")
	public void findPupil() {
		// 查询年龄小于18岁的user
		Query query = new Query(Criteria.where("age").lt(18));
		List<User> userList = mongoTemplate.find(query, User.class);
		logger.info("find {} pupils", userList.size());
		for (int i = 0; i < userList.size(); i++) {
			if (USER_QUEUE.size() < 100) {
				USER_QUEUE.add(userList.get(i));
			} else {
				break;
			}
		}
	}

	// 每秒执行一次
	@Scheduled(fixedRate = 1000 * 5)
	public void deletePupil() {
		// 删除年龄小于18岁的学生
		User user = USER_QUEUE.poll();
		if (user != null) {
			Criteria c = Criteria.where(Key.ID).is(user.getId());
			Query q = new Query(c);
			logger.info("delete q:{}", q);
			mongoTemplate.remove(q, User.class);
		}
	}

}
