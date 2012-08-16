package com.jg.onofflist.server.repository;


import com.google.appengine.api.datastore.Key;
import com.jg.core.client.model.Creater;
import com.jg.onofflist.client.model.OnOffListId;

import java.io.Serializable;

/**
 *
 */
public class CreateListItem extends Creater implements Serializable {

    private static final long serialVersionUID = 6372618919876531337L;

    private OnOffListId parent;
    private String name;
    private Boolean on;
    private long id;


    public CreateListItem() {
    }

    public CreateListItem(OnOffListId parent, String name, Boolean on, long id) {
        this.parent = parent;
        this.name = name;
        this.on = on;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OnOffListId getParent() {
        return parent;
    }

    public void setParent(OnOffListId parent) {
        this.parent = parent;
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
}