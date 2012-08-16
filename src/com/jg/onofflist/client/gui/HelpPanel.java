package com.jg.onofflist.client.gui;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

/**
 *
 */
public class HelpPanel extends FlowPanel{
    private FlowPanel innerPanel = new FlowPanel();
    private HTML htmlText;

    public HelpPanel(String ... text) {
        super();
        getElement().getStyle().setBackgroundColor("white");
        innerPanel.setStyleName("listpage_help");
        htmlText = new HTML(buildText(text));

        add(innerPanel);
        innerPanel.add(htmlText);
    }

    private String buildText(String[] text) {
        StringBuilder sb = new StringBuilder();
        for (String s : text) {
            sb.append("<p>");
            sb.append(s);
            sb.append("</p>");
        }
        return sb.toString();

    }

}
