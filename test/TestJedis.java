import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class TestJedis {

    public static ApplicationContext ctx;
    static Logger logger = Logger.getLogger(TestJedis.class.getName());
    public RedisConnection redisConnection;
    // 简单测试JedisConnection
    @Ignore
    @Test
    public void test1() {
        ctx = new ClassPathXmlApplicationContext("resource/spring-redis.xml");
        RedisTemplate<String, String> template = (RedisTemplate) ctx
                .getBean("redisTemplate");
        SetOperations<String, String> setOps=template.opsForSet();
        try {
            Set<String> bookur=setOps.members("bookUrl");
            int i=bookur.size();
            for (String URL:bookur)
                logger.info(URL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void test2() {
        Set<byte[]> keys = redisConnection.keys(new String("*").getBytes());
        for (Iterator<byte[]> iter = keys.iterator(); iter.hasNext();) {
            System.out.println(new String(iter.next()));
        }
    }

}