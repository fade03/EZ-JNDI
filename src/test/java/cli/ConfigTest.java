package cli;

import com.beust.jcommander.JCommander;
import org.junit.Assert;
import org.junit.Test;

public class ConfigTest {
    @Test
    public void testParams() {
        String[] args = new String[]{"-hp=8081", "-lp=2099", "-c=whoami"};
        Config config = new Config();
        JCommander.newBuilder().addObject(config).build().parse(args);

        Assert.assertEquals(8081, config.hp);
        Assert.assertEquals(2099, config.lp);
        Assert.assertEquals("whoami", config.command);
    }
}
