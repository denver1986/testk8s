package top.gwssi.k8s.config;

import java.net.URI;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolUtil {

    private static JedisPool pool = null;
    private static final int PORT = 6379 ;
    private static final String HOST = "dangling-bunny-redis" ;
    private static void createJedisPool() {

		String password = System.getenv("REDIS_PASSWORD") ;
		System.out.println("REDIS_PASSWORD === " + password);
		String uri = "redis://nil:"+password+"@"+HOST+":"+PORT ;
		System.out.println("uri === " + uri);
		
    	pool = new JedisPool(URI.create(uri));  
    }
    
    private static synchronized void poolInit() {
        if (pool == null)
            createJedisPool();
    }
    
    public static Jedis getJedis() {
        if (pool == null)
            poolInit();
        return pool.getResource();
    }
    
    public static void close(Jedis jedis) {
    	jedis.close();
    }

    
    
    public static void main(String[] args) {
		
    	String host = "redis://nil:123123@10.10.1.1:6375" ;

        URI uri = URI.create(host);

   	 System.out.println(uri.getPort());
    	
    	 String userInfo = uri.getUserInfo();
    	 System.out.println(userInfo);
    	 String password = userInfo.split(":", 2)[1];
    	 System.out.println(password);
    	
	}
}
