package com.jg.onofflist.client.model;

import com.jg.core.client.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class OnOffList extends Model<OnOffListId>{
    private static final long serialVersionUID = -8704050322726213639L;

    private String url;
    private List<OnOffListItem> items = new ArrayList<OnOffListItem>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<OnOffListItem> getItems() {
        return items;
    }

    public void setItems(List<OnOffListItem> items) {
        this.items = items;
    }
}
