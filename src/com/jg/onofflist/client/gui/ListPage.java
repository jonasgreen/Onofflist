package com.jg.onofflist.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.jg.core.client.service.CallBack;
import com.jg.core.client.service.SingleResult;
import com.jg.onofflist.client.AddPanel;
import com.jg.onofflist.client.model.OnOffList;
import com.jg.onofflist.client.model.OnOffListItem;
import com.jg.onofflist.client.model.OnOffListItemListener;
import com.jg.onofflist.client.service.OnOffService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ListPage extends FlowPanel implements OnOffListItemListener {

    private ListComponent onListComp;
    private ListComponent offListComp;
    private OnOffList modelList;
    private AddPanel addPanel;

    private HelpPanel helpPanel;

    public ListPage(SingleResult<OnOffList> result) {
        super();
        this.modelList = result.getResult();
        this.setStyleName("listpage");
        add(getAddPanel());

        List<OnOffListItem> onList = new ArrayList<OnOffListItem>();
        List<OnOffListItem> offList = new ArrayList<OnOffListItem>();

        for (OnOffListItem item : modelList.getItems()) {
            item.addListener(this);
            if (item.getOn()) {
                onList.add(item);
            }
            else {
                offList.add(item);
            }
        }

        this.onListComp = new ListComponent(onList, this);
        add(onListComp);
        onListComp.setStyleName("onlist");

        this.offListComp = new ListComponent(offList, this);
        add(offListComp);
        offListComp.setStyleName("offlist");

        numberOfItemsChanged();


    }

    public static void showPopup(String text) {
        final PopupPanel popUp = new PopupPanel(false, true);
        popUp.setStyleName("popup");
        FlowPanel all = new FlowPanel();
        all.setStyleName("popup_all");
        FlowPanel top = new FlowPanel();
        top.setStyleName("popup_top");
        FlowPanel bottom = new FlowPanel();
        bottom.setStyleName("popup_bottom");
        Label l = new Label(text+".");
        top.add(l);

        FlowPanel medium = new FlowPanel();
        medium.add(new Label("Check your internet connection."));
        medium.setStyleName("popup_medium");

        Button ok = new Button("Ok");
        ok.addStyleName("popup_button");
        ok.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                popUp.hide();
            }
        });
        bottom.add(ok);

        all.add(top);
        all.add(medium);
        all.add(bottom);
        popUp.add(all);
        popUp.show();
        popUp.center();

    }


    public AddPanel getAddPanel() {
        if (addPanel == null) {
            addPanel = new AddPanel(this);
        }
        return addPanel;
    }

    public void createNewItem(final String text) {
        OnOffService.getInstance().createListItem(modelList.getId(), text, new CallBack<SingleResult<OnOffListItem>>() {
            @Override
            public void success(SingleResult<OnOffListItem> result) {
                result.getResult().addListener(ListPage.this);
                onListComp.addIt(result.getResult());
                numberOfItemsChanged();
            }

            @Override
            public void fail(Throwable t) {
                ListPage.showPopup("Unable to create item: '"+text+"'");
            }
        });
    }

    public void numberOfItemsChanged() {
        int count = countItems();
        if (count == 0) {

            setNewHelp("Type your first todo item to begin.", "Don't forget to bookmark this list for later access.", getShareThisList());
        }
        else if (count < 4 && count > 0) {
            setNewHelp("Switch an item ON/OFF by clicking/tapping its name", getShareThisList());
        }
        else{
            setNewHelp(null);
        }

    }

    private String getShareThisList() {
        return "<div class=\"sharediv\">Share this list:<div class=\"shareurl\">http://onofflist.com/#id="+modelList.getUrl()+"</div></div>";
    }

    private void setNewHelp(String... text) {

        if (helpPanel != null) {
            helpPanel.removeFromParent();
        }
        if (text != null) {
            helpPanel = new HelpPanel(text);
            add(helpPanel);
        }
    }

    private int countItems() {
        return onListComp.countItems() + offListComp.countItems();
    }


    public void onChange(OnOffListItem item) {
        try {
            if (item.getOn()) {
                offListComp.removeIt(item);
                onListComp.addIt(item);
            }
            else {
                onListComp.removeIt(item);
                offListComp.addIt(item);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
