package server.ldap;

import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.listener.interceptor.InMemoryOperationInterceptor;

public class RSOperationInterceptor extends InMemoryOperationInterceptor {
    private String command;

    public RSOperationInterceptor(String command) {
        this.command = command;
    }

    @Override
    public void processSearchResult(InMemoryInterceptedSearchResult result) {
        // TODO: return serialization data directly
    }
}
