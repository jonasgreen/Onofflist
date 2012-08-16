package com.jg.onofflist.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.jg.core.client.service.CallBack;
import com.jg.core.client.service.SingleResult;
import com.jg.core.client.util.UrlUtil;
import com.jg.onofflist.client.model.OnOffList;
import com.jg.onofflist.client.gui.ListPage;
import com.jg.onofflist.client.gui.StartPage;
import com.jg.onofflist.client.service.OnOffService;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

    public void onModuleLoad() {
        History.addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> stringValueChangeEvent) {
                loadPage(History.getToken());
            }
        });

        UrlUtil.init("onofflist");
        loadPage(History.getToken());
    }

    private void loadPage(String token) {
        if (token == null || token.isEmpty()) {
            createNewList();
        }
        else {
            loadListPage(token);
        }


    }

    private void createNewList() {
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

    private void loadFrontPage(String token) {
        AppManager.loadPage(new StartPage(token));
    }

    private void loadListPage(final String token) {
        try {
            OnOffService.getInstance().getOnOffList(token.replace("id=", ""), new CallBack<SingleResult<OnOffList>>() {
                @Override
                public void success(SingleResult<OnOffList> result) {
                    if (result.getResult() == null) {
                        loadFrontPage(token);
                    }
                    else {
                        ListPage page = new ListPage(result);
                        AppManager.loadPage(page);
                        if(result.getResult().getItems().isEmpty()){
                            page.getAddPanel().getTextBox().setFocus(true);
                        }
                    }
                }

                @Override
                public void fail(Throwable t) {
                    loadFrontPage(token);
                }
            });
        }
        catch (Exception e) {
            loadFrontPage(token);
        }

    }

}
