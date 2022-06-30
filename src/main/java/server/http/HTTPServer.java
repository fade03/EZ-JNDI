package server.http;

import cli.Config;
import com.sun.net.httpserver.HttpServer;
import server.Server;
import server.http.handler.ExecHandler;
import server.http.handler.SimHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HTTPServer implements Server {

    private final Config cfg;

    public HTTPServer(Config cfg) {
        this.cfg = cfg;
    }

    @Override
    public void run() {
        int hp = cfg.hp;
//        String command = cfg.command;
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(hp), 0);
            httpServer.createContext("/Foo.class", new ExecHandler());
            httpServer.createContext("/Sim.class", new SimHandler());
            httpServer.start();
            System.out.printf("[HTTP] listen on %d...\n", hp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
