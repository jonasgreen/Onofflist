package com.jg.onofflist.client.model;

import com.jg.core.client.service.DefaultAction;
import com.jg.core.client.service.SingleResult;
import com.jg.core.client.service.VoidResult;


/**
 *
 */
public class UpdateListItemAction extends DefaultAction<VoidResult> {

    private static final long serialVersionUID = -5147960250101611497L;
    private OnOffListItem item;


    public UpdateListItemAction() {
    }

    public UpdateListItemAction(OnOffListItem item) {
        this.item = item;
    }

    public OnOffListItem getItem() {
        return item;
    }

    public void setItem(OnOffListItem item) {
        this.item = item;
    }

}