package com.jg.onofflist.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.jg.core.client.service.CallBack;
import com.jg.core.client.service.SingleResult;
import com.jg.onofflist.client.AppManager;
import com.jg.onofflist.client.model.OnOffList;
import com.jg.onofflist.client.service.OnOffService;

/**
 *
 */
public class StartPage extends FlowPanel{


    private Button createListButton;

    public StartPage(String token) {
        super();
        
        FlowPanel all = new FlowPanel();
        all.setStyleName("startpage_all");
        
        FlowPanel top = new FlowPanel();
        top.setStyleName("startpage_top");

        top.add(new Label("Unable to load todo list from id-token: "+token));
        
        FlowPanel buttonPanel = new FlowPanel();
        buttonPanel.setStyleName("startpage_bottom");
        buttonPanel.add(getCreateListButton());

        FlowPanel noAccountPanel = new FlowPanel();
        noAccountPanel.add(new Label("No account required"));
        noAccountPanel.setStyleName("startpage_noaccount");


        all.add(top);
        all.add(buttonPanel);
        all.add(noAccountPanel);

        add(all);
    }


    public Button getCreateListButton() {
        if (createListButton == null) {
            createListButton = new Button("Create a new list");
            createListButton.setStyleName("colorbutton3");
            createListButton.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    OnOffService.getInstance().createOnOffList(new CallBack<SingleResult<OnOffList>>() {
                        @Override
                        public void success(SingleResult<OnOffList> result) {
                            ListPage page = new ListPage(result);
                            AppManager.loadPage(page);
                            History.newItem("id=" + result.getResult().getUrl(), false);
                            page.getAddPanel().getTextBox().setFocus(true);
                        }

                        @Override
                        public void fail(Throwable t) {
                            t.printStackTrace();
                        }
                    });

                }
            });
        }
        return createListButton;
    }
}
