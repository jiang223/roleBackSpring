//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.SetOperations;
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.model.AfterExtractor;
//import us.codecraft.webmagic.model.ConsolePageModelPipeline;
//import us.codecraft.webmagic.model.OOSpider;
//import us.codecraft.webmagic.model.annotation.TargetUrl;
//import java.util.List;
//import java.util.logging.Logger;
//
///**
// * Created by a9805943 on 2017/4/13.
// */
//@TargetUrl("http://www.fhxsw.org/(?!read)*")
//public class CapBookUrl implements AfterExtractor {
//    public static ApplicationContext ctx;
//    static Logger logger = Logger.getLogger(GithubRepo.class.getName());
////    @ExtractBy("//h1/text()")
////    private String bookName;
////    @ExtractBy("//h1/text()")
////    private List<String> bookUrl;
//    @Override
//    public void afterProcess(Page page) {
//        List<String>bookUrl=page.getHtml().links().regex("http://www.fhxsw.org/read/\\d{1,5}/").all();
//        inSO(getSo(),bookUrl);
//
//    }
//    public static void main(String[] args) {
//        ctx = new ClassPathXmlApplicationContext("resource/spring-redis.xml");
//        RedisTemplate<String, String> template = (RedisTemplate) ctx
//                .getBean("redisTemplate");
//        OOSpider.create(Site.me().setSleepTime(1000)
//                , new ConsolePageModelPipeline(), CapBookUrl.class)
//                .addUrl("http://www.fhxsw.org").thread(5).run();
//
//    }
//    public  SetOperations<String, String> getSo(){
//        RedisTemplate<String, String> template = (RedisTemplate) ctx
//                .getBean("redisTemplate");
//        return  template.opsForSet();
//    }
//    public void inSO(SetOperations<String, String>getSo , List<String>bookUrl){
//        for(String url:bookUrl){
//            getSo.add("bookUrl",url);
//        }
//
//    }
//}
