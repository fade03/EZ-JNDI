package server.ldap;

import cli.Config;
import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import server.Server;
import server.ldap.dispatcher.OperationDispatcher;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.net.InetAddress;

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
        int lp = this.cfg.lp;
        try {
            InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig(LDAP_BASE);
            config.setListenerConfigs(new InMemoryListenerConfig(
                    "listen",
                    InetAddress.getByName("0.0.0.0"),
                    lp,
                    ServerSocketFactory.getDefault(),
                    SocketFactory.getDefault(),
                    (SSLSocketFactory) SSLSocketFactory.getDefault()
            ));

            config.addInMemoryOperationInterceptor(new OperationDispatcher());
//            config.addInMemoryOperationInterceptor(new OperationInterceptor(new URL(codebase)));
//            cfg.addInMemoryOperationInterceptor(new RSOperationInterceptor(this.cfg.command));

            InMemoryDirectoryServer ds = new InMemoryDirectoryServer(config);
            ds.startListening();
            System.out.printf("[LDAP] listen on %d...\n", lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
