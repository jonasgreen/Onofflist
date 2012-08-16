package com.jg.onofflist.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.jg.core.client.service.CallBack;
import com.jg.core.client.service.VoidResult;
import com.jg.onofflist.client.model.OnOffListItem;
import com.jg.onofflist.client.service.OnOffService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class ListComponent extends FlowPanel {

    private static Comparator<ListItem> comp = new Comparator<ListItem>() {
        public int compare(ListItem item1, ListItem item2) {
            return item1.getModel().getName().compareTo(item2.getModel().getName());
        }
    };

    private List<ListItem> items = new ArrayList<ListItem>();
    private ListPage parent;

    public ListComponent(List<OnOffListItem> list, ListPage parent) {
        this.parent = parent;
        for (OnOffListItem mItem : list) {
            createGuiItem(mItem);
        }
        Collections.sort(items, comp);
        for (ListItem item : items) {
            add(item);
        }
    }


    public void removeIt(ListItem item) {
        items.remove(item);
        item.removeFromParent();
    }

    public void deleteIt(final ListItem item) {
        removeIt(item);
        parent.numberOfItemsChanged();
        OnOffService.getInstance().deleteItem(item.getModel(), new CallBack<VoidResult>() {
            @Override
            public void success(VoidResult result) {
            }

            @Override
            public void fail(Throwable t) {                
                ListPage.showPopup("Unable to delete item: '"+item.getModel().getName()+"'");
                addIt(item.getModel());
            }
        });
    }


    public void removeIt(OnOffListItem item) {
        removeIt(getListItem(item));
    }

    public ListItem getListItem(OnOffListItem item) {
        for (ListItem i : items) {
            if (i.getModel().equals(item)) {
                return i;
            }
        }
        return null;
    }

    public void addIt(OnOffListItem item) {
        ListItem listItem = createGuiItem(item);
        Collections.sort(items, comp);
        insert(listItem, items.indexOf(listItem));
    }

    private ListItem createGuiItem(OnOffListItem item) {
        ListItem listItem;
        listItem = new ListItem(item);
        listItem.setParent(this);
        items.add(listItem);
        return listItem;
    }

    public int countItems() {
        return items.size();
    }
}
