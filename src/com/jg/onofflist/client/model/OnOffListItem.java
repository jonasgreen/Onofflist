package com.jg.onofflist.client.model;

import com.jg.core.client.model.Model;
import com.jg.onofflist.client.gui.ListPage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class OnOffListItem extends Model<OnOffListItemId>{
    private static final long serialVersionUID = -7483598650517700607L;

    private String name;
    private Boolean on;
    private transient List<OnOffListItemListener> listeners = new ArrayList<OnOffListItemListener>();

    public OnOffListItem(String name, Boolean on) {
        this.name = name;
        this.on = on;
    }

    public OnOffListItem() {
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
        notifyListeners();
    }
    
    public void addListener(OnOffListItemListener l){
        listeners.add(l);
    }
    
    public void notifyListeners(){

        for (OnOffListItemListener l : listeners) {
            l.onChange(this);
        }
    }
}
