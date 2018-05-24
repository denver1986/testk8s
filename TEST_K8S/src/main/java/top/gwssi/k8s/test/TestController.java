package top.gwssi.k8s.test;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;
import top.gwssi.k8s.config.JedisPoolUtil;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private MongoTemplate mongoTemplate;

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping(value = "/1", produces = "application/json;charset=UTF-8")
	public String test1() {
		
		logger.info("mongoTemplate = " + mongoTemplate);
		List<Userinfo> findAll = mongoTemplate.findAll(Userinfo.class, "userinfo") ;
		logger.info("findAll.size = " + findAll.size());
		
		logger.info("Userinfo = " + findAll.get(0).getUsername());
		return findAll.get(0).getUsername() ;
	}
	
	@RequestMapping(value = "/2", produces = "application/json;charset=UTF-8")
	public String test2() {
		Jedis jedis = JedisPoolUtil.getJedis() ;
		logger.info("jedis = " + jedis.toString());
		logger.info("jedis.clientList = " + jedis.clientList());
		
		String username = jedis.get("username") ;
		logger.info("username = " + username);
		
		jedis.close();
		
		return username ;
	}
	

	@RequestMapping(value = "/3/{number}", produces = "application/json;charset=UTF-8")
	public String test3(@PathVariable(required = true, value = "number") double number) {
		for(double i =0; i < number; i++) {
			Math.sqrt(i) ;
		}
    	return "success" ;
	}
	
}
