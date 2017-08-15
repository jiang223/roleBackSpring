import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import java.util.List;

//http://www.fhxsw.org/read/7672
public class GithubRepo implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        if (page.getRequest().getUrl().equals("http://www.fhxsw.org/read/7672"))
        {
            List<String> list=page.getHtml().links().regex("(http://www.fhxsw.org/read/7672/\\d+.html)").all();
            page.addTargetRequests(page.getHtml().links().regex("(http://www.fhxsw.org/read/7672/\\d+.html)").all());
        }else{

        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepo()).addUrl("http://www.fhxsw.org/read/7672").thread(15).run();
    }
}