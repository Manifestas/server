package main;

import accounts.AccountService;
import accounts.UserProfile;
import dataSets.UsersDataSet;
import dbService.DBService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hibernate.HibernateException;
import servlets.SessionsServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();
        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        contextHandler.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("public_html");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, contextHandler});

        Server server = new Server(8080);
        server.setHandler(handlerList);

        server.start();
        System.out.println("Server started");

        DBService dbService = new DBService();
        dbService.printConnectInfo();
        try {
            long userId = dbService.addUser("tully");
            System.out.println("Added user id: " + userId);

            UsersDataSet usersDataSet = dbService.getUser(userId);
            System.out.println("User data set: " + usersDataSet);
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        server.join();


    }
}
