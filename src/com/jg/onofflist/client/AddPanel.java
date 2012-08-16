package com.jg.onofflist.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.jg.onofflist.client.gui.ListPage;


/**
 *
 */
public class AddPanel extends FlowPanel{

    private TextBox textBox;
    private Label plus;
    private ListPage parent;
    private Label share;

    public AddPanel(ListPage parent) {
        this.parent = parent;
        setStyleName("addPanel");
        FlowPanel div = new FlowPanel();
        div.add(getTextBox());
        div.add(getPlus());
        add(div);
    }


    public TextBox getTextBox() {
        if (textBox == null) {
            textBox = new TextBox();
            textBox.setStyleName("textBox");
            textBox.addKeyDownHandler(new KeyDownHandler() {
                public void onKeyDown(KeyDownEvent event) {
                    if(KeyCodes.KEY_ENTER == event.getNativeKeyCode()){
                        createNewItem();
                    }
                }
            });
        }
        return textBox;
    }

    public Label getPlus() {
        if (plus == null) {
            plus = new Label("+");
            plus.setStyleName("plus");
            plus.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    createNewItem();
                }
            });

        }
        return plus;
    }

    private void createNewItem() {
        if(getTextBox().getText() == null || getTextBox().getText().equals("")){
            return;
        }
        parent.createNewItem(getTextBox().getText());
        getTextBox().setText("");
    }
}
