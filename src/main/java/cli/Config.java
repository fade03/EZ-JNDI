package cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;


@Parameters(separators = "=")
public class Config {
    private static final Config cfg = new Config();

    private Config(){}

    public static Config getGlobalInstance() {
        return cfg;
    }

    @Parameter(names = {"-lp"}, description = "LDAP Port", order = 0)
    public int lp = 1099;
    @Parameter(names = {"-hp"}, description = "HTTP Port", order = 1)
    public int hp = 8080;
    @Parameter(names = {"-c", "--command"}, description = "Command", order = 2)
    public String command = "open -a Calculator";
    @Parameter(names = {"-h", "--help"}, help = true)
    public boolean help;
}
