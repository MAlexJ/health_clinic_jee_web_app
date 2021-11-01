package com.malex.controllers.listeners;

import com.malex.dao.database.Database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener  implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Database.initConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Database.destroyConnection();
    }
}
