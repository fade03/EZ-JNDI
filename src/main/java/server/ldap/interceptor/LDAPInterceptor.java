package server.ldap.interceptor;

import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPBindException;
import com.unboundid.ldap.sdk.LDAPException;

import java.io.IOException;

public interface LDAPInterceptor {
    void sendResult(Entry entry, InMemoryInterceptedSearchResult result) throws Exception;
}
