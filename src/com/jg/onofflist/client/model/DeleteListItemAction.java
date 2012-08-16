package com.jg.onofflist.client.model;

import com.jg.core.client.service.DefaultAction;
import com.jg.core.client.service.VoidResult;


/**
 *
 */
public class DeleteListItemAction extends DefaultAction<VoidResult> {

    private static final long serialVersionUID = 7620126503877847632L;
    private OnOffListItem item;


    public DeleteListItemAction() {
    }

    public DeleteListItemAction(OnOffListItem item) {
        this.item = item;
    }

    public OnOffListItem getItem() {
        return item;
    }

    public void setItem(OnOffListItem item) {
        this.item = item;
    }

}