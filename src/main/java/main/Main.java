package main;

import accounts.AccountService;
import chat.WebSocketChatServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.TestResource;
import resources.TestResourceServer;
import resources.TestResourceServerController;
import resources.TestResourceServerControllerMBean;
import servlets.ResourceServlet;
import servlets.SessionsServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        TestResourceServer resourceServer = new TestResourceServer(new TestResource());
        TestResourceServerControllerMBean resourceServerController = new TestResourceServerController(resourceServer);

        AccountService accountService = new AccountService();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new ResourceServlet(resourceServerController)), "/resources");
        contextHandler.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        contextHandler.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        contextHandler.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, contextHandler});

        Server server = new Server(8080);
        server.setHandler(handlerList);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
