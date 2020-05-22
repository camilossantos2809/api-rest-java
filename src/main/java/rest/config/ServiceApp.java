package rest.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class ServiceApp {
    public static void main(String[] args) {
        Server jettyServer = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        jettyServer.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
//        jerseyServlet.setInitParameter("jersey.config.server.provider.packages","rest.resource");
        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            jettyServer.destroy();
        }
    }
}
