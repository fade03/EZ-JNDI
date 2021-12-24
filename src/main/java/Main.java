import cli.Config;
import com.beust.jcommander.JCommander;
import server.http.HTTPServer;
import server.ldap.LDAPServer;

public class Main {
    public static void main(String[] args) {
        // -lp=1099 -model=cb -hp=8080 -c=calc
        // -lp=1099 -model=rs -c=calc
        Config config = new Config();
        JCommander cli = JCommander.newBuilder().addObject(config).build();
        cli.parse(args);

        if (config.help) {
            cli.usage();
            return;
        }
        new Thread(new HTTPServer().acceptCfg(config)).start();
        new Thread(new LDAPServer().acceptCfg(config)).start();
    }
}
