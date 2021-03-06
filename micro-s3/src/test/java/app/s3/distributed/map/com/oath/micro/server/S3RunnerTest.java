package app.s3.distributed.map.com.oath.micro.server;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.oath.micro.server.MicroserverApp;
import com.oath.micro.server.testing.RestAgent;

@Ignore
public class S3RunnerTest {

    RestAgent rest = new RestAgent();

    MicroserverApp server;

    @Before
    public void startServer() {

        server = new MicroserverApp(
                                    () -> "simple-app");

        server.start();

    }

    @After
    public void stopServer() {
        server.stop();
    }

    @Test
    public void runAppAndBasicTest() throws InterruptedException, ExecutionException {
        rest.get("http://localhost:8080/simple-app/s3/put");
        System.out.println("hello");
        assertThat(rest.get("http://localhost:8080/simple-app/s3/get"), containsString("world"));

    }

}
