package com.jg.onofflist.client.model;

import com.jg.core.client.service.DefaultAction;
import com.jg.core.client.service.SingleResult;


/**
 *
 */
public class GetOnOffListAction extends DefaultAction<SingleResult<OnOffList>> {


    private static final long serialVersionUID = -4873711741024285585L;

    private String urlId;

    public GetOnOffListAction() {
    }

    public GetOnOffListAction(String urlId) {
        this.urlId = urlId;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }
}