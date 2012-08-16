package com.jg.onofflist.client.model;

import com.jg.core.client.service.DefaultAction;
import com.jg.core.client.service.SingleResult;


/**
 *
 */
public class CreateListItemAction extends DefaultAction<SingleResult<OnOffListItem>> {


    private static final long serialVersionUID = -6102804049382090746L;

    private OnOffListId parentId;
    private String name;
    private Boolean on;


    public CreateListItemAction() {
    }

    public CreateListItemAction(OnOffListId parentId, String name, Boolean on) {
        this.parentId = parentId;
        this.name = name;
        this.on = on;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOn() {
        return on;
    }

    public void setOn(Boolean on) {
        this.on = on;
    }

    public OnOffListId getParentId() {
        return parentId;
    }

    public void setParentId(OnOffListId parentId) {
        this.parentId = parentId;
    }
}