package server.ldap.interceptor;

import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPException;

/**
 * return serialization data directly
 */
public class RSOperationInterceptor implements LDAPInterceptor {
    private String command;

    public RSOperationInterceptor(String command) {
        this.command = command;
    }

    @Override
    public void sendResult(Entry entry, InMemoryInterceptedSearchResult result) throws LDAPException {
        // TODO: return serialization data directly
    }
}
