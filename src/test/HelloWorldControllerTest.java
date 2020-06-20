package springboot;

import junit.framework.TestCase;
import org.junit.Test;
import springboot.controller.HelloWorldController;

/**
 * Created by Administrator on 2017/9/27.
 */
public class HelloWorldControllerTest extends TestCase {
    @Test
    public void testSayHello() {
        assertEquals("Hello,World!", new IndexControler().);
    }
}