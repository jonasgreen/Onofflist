package com.jg.onofflist.client.gui;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.jg.core.client.service.CallBack;
import com.jg.core.client.service.VoidResult;
import com.jg.onofflist.client.App;
import com.jg.onofflist.client.AppManager;
import com.jg.onofflist.client.model.OnOffListItem;
import com.jg.onofflist.client.service.OnOffService;

/**
 *
 */
public class ListItem extends FlowPanel implements ClickHandler, MouseOverHandler {
    private static int count = 0;

    private Label textLabel;
    private Label deleteLabel;
    private final int id;


    private ListComponent parent;

    private OnOffListItem model;

    public void setParent(ListComponent parent) {
        this.parent = parent;
    }

    public ListItem(OnOffListItem item) {
        this.id = count++;
        this.model = item;

        this.add(getTextLabel());
        this.add(getDeleteLabel());
        this.getTextLabel().setText(item.getName());

        style();
        addDomHandler(this, ClickEvent.getType());
        addDomHandler(this, MouseOverEvent.getType());
    }

    private void style() {
        if (model.getOn()) {
            setStyleName("listitem_on");
        }
        else {
            setStyleName("listitem_off");
        }

    }


    public Label getTextLabel() {
        if (textLabel == null) {
            textLabel = new Label();
            textLabel.setStyleName("name");
            textLabel.addMouseOverHandler(new MouseOverHandler() {
                public void onMouseOver(MouseOverEvent event) {
                    textLabel.getElement().getStyle().setCursor(Style.Cursor.POINTER);
                }
            });
            textLabel.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    //clicked();
                }
            });
        }
        return textLabel;
    }

    private void clicked() {
        try {
            if (model.getOn()) {
                setStyleName("listitem_off");
            }
            else {
                setStyleName("listitem_on");
           }

            Timer t = new Timer() {
                @Override
                public void run() {
                    changeState();
                }
            };

            t.schedule(500);

        }
        catch (Exception e) {
            AppManager.error(e.getMessage());
        }
    }

    private void changeState() {
        model.setOn(!model.getOn());
        OnOffService.getInstance().updateListItem(model, new CallBack<VoidResult>() {
            @Override
            public void success(VoidResult result) {
            }

            @Override
            public void fail(Throwable t) {
                ListPage.showPopup("Unable to change item: '"+model.getName()+"'");
                model.setOn(!model.getOn());
            }
        });
    }


    public Label getDeleteLabel() {
        if (deleteLabel == null) {
            deleteLabel = new Label("-");
            deleteLabel.setStyleName("minusAndPlus");
            deleteLabel.addMouseOverHandler(new MouseOverHandler() {
                public void onMouseOver(MouseOverEvent event) {
                    deleteLabel.getElement().getStyle().setCursor(Style.Cursor.POINTER);
                }
            });
            deleteLabel.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    parent.deleteIt(ListItem.this);
                }
            });
        }
        return deleteLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ListItem listItem = (ListItem) o;

        if (id != listItem.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public void onClick(ClickEvent event) {
        clicked();
    }

    public void onMouseOver(MouseOverEvent event) {
        getElement().getStyle().setCursor(Style.Cursor.POINTER);
    }

    public OnOffListItem getModel() {
        return model;
    }
}
