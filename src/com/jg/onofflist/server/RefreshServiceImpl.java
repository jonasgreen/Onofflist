package com.jg.onofflist.server;

import com.jg.core.server.Logger;
import com.jg.core.server.services.ActionHandlerRegistry;
import com.jg.onofflist.client.model.RefreshAction;
import com.jg.onofflist.server.services.RefreshActionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class RefreshServiceImpl extends HttpServlet {
    private static final long serialVersionUID = 274804192869160632L;


    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request);
    }


    private void handleRequest(HttpServletRequest request) {
        try {
            Logger.log("Executing refresh cron job");
            new RefreshActionHandler().execute(new RefreshAction());
        }
        catch (Exception e) {
            Logger.log("Cron job", e);

        }

    }
}
