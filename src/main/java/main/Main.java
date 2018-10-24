package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {
    public static void main(String[] args) throws Exception{
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        Server server = new Server(8080);
        server.setHandler(handler);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
