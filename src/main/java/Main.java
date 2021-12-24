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

        banner();
        new Thread(new HTTPServer().acceptCfg(config)).start();
        new Thread(new LDAPServer().acceptCfg(config)).start();
    }

    public static void banner() {
        System.out.println("\n" +
                " ______     ______       __     __   __     _____     __    \n" +
                "/\\  ___\\   /\\___  \\     /\\ \\   /\\ \"-.\\ \\   /\\  __-.  /\\ \\   \n" +
                "\\ \\  __\\   \\/_/  /__   _\\_\\ \\  \\ \\ \\-.  \\  \\ \\ \\/\\ \\ \\ \\ \\  \n" +
                " \\ \\_____\\   /\\_____\\ /\\_____\\  \\ \\_\\\\\"\\_\\  \\ \\____-  \\ \\_\\ \n" +
                "  \\/_____/   \\/_____/ \\/_____/   \\/_/ \\/_/   \\/____/   \\/_/ \n" +
                "                                                            \n");
    }
}
