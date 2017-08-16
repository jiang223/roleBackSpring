import com.gaussic.dao1.BookMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by a9805943 on 2017/4/15.
 */
public class BookTest {
    public static ApplicationContext ctx;
    final int comint=5000;
    @Test
    public void testInsert(){
        ctx = new ClassPathXmlApplicationContext("resource/mybatis.xml");
        BookMapper bookMapper = (BookMapper) ctx
                .getBean("bookMapper");
        RedisTemplate<String, String> template = (RedisTemplate) ctx
                .getBean("redisTemplate");
        SetOperations<String, String> setOps=template.opsForSet();
        Set<String> bookUrls=setOps.members("bookUrl");
        List<String>urlList=new ArrayList<>(bookUrls);
        int yu=urlList.size()%comint;
        int count=urlList.size()/comint;
        for(int i=0;i<count;i++){
            if (i==0){
                bookMapper.batchInsert(urlList.subList(0,comint));
            }
            else  if (i>0&&i<count-1){
                bookMapper.batchInsert(urlList.subList(i*comint,(i+1)*comint));
            }
            else{
                bookMapper.batchInsert(urlList.subList(i*comint,urlList.size()-1));
            }
        }


    }
}
