package servlets.listener;

import database.LoginDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;


@WebListener
public class ContextListener implements ServletContextListener {

    private AtomicReference<LoginDAO> dao;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        dao = new AtomicReference<>(new LoginDAO());

        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("dao", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao = null;
    }
}
