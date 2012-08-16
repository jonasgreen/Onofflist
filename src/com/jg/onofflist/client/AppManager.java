package com.jg.onofflist.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class AppManager {
    private static Widget showingPage;
    private static ScrollPanel scrollPanel;

    public static void error(String s){
        loadPage(new HTML("ERROR: "+s));
    }
    
    public static void loadPage(Widget page){
        if(showingPage != null){
            showingPage.removeFromParent();
        }
        showingPage = page;
        getScrollPanel().add(showingPage);

    }

    private static ScrollPanel getScrollPanel() {
        if (scrollPanel == null) {
            scrollPanel = new ScrollPanel();
            RootLayoutPanel.get().add(scrollPanel);

        }
        return scrollPanel;
    }
}
