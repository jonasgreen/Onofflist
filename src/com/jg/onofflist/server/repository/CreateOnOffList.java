package com.jg.onofflist.server.repository;


import com.jg.core.client.model.Creater;

import java.io.Serializable;

/**
 *
 */
public class CreateOnOffList extends Creater implements Serializable {

    private static final long serialVersionUID = 6506899997788823805L;
    private long id;


    public CreateOnOffList() {
    }

    public CreateOnOffList(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}