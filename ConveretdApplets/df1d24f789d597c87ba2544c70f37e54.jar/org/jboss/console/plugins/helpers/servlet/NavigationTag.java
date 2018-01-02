// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers.servlet;

import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import java.util.ArrayList;
import javax.servlet.jsp.tagext.TagSupport;

public class NavigationTag extends TagSupport
{
    private ArrayList tabs;
    private String selectedTabName;
    
    public NavigationTag() {
        this.tabs = new ArrayList(10);
    }
    
    public int doStartTag() throws JspTagException {
        this.tabs.clear();
        return 1;
    }
    
    public int doEndTag() throws JspTagException {
        try {
            this.pageContext.getOut().write("<table width='100%' height='24' border='0' cellspacing='0' cellpadding='0'>");
            this.pageContext.getOut().write("<tr valign='bottom'>");
            for (int i = 0; i < this.tabs.size(); ++i) {
                final Tab tab = this.tabs.get(i);
                final String name = tab.getName();
                this.pageContext.getOut().write("<td width='8' align='left' class='tabSpacer'>");
                this.pageContext.getOut().write("<p><img src='images/spacer.gif' width='8' height='24'></p>");
                this.pageContext.getOut().write("</td>");
                this.pageContext.getOut().write("<td align='left' nowrap class=");
                if (this.isSelected(tab)) {
                    this.pageContext.getOut().write("'tab'>");
                }
                else {
                    this.pageContext.getOut().write("'tabOff'>");
                }
                this.pageContext.getOut().write("<p>");
                if (tab.getHref() != null) {
                    this.pageContext.getOut().write("<a href='" + tab.getHref() + "'>");
                }
                this.pageContext.getOut().write(name);
                if (tab.getHref() != null) {
                    this.pageContext.getOut().write("</a>");
                }
                this.pageContext.getOut().write("</p></td>");
            }
            this.pageContext.getOut().write("<td width='100%' align='left' class='tabSpacer'><p>&nbsp;</p></td>");
            this.pageContext.getOut().write("</tr>");
            this.pageContext.getOut().write("</table>");
        }
        catch (IOException e) {
            throw new JspTagException(e.toString());
        }
        return 6;
    }
    
    private boolean isSelected(final Tab tab) {
        boolean selected = false;
        if (tab.isSelected()) {
            selected = true;
        }
        if (this.selectedTabName != null && !this.selectedTabName.equals("")) {
            selected = tab.getName().equals(this.selectedTabName);
        }
        return selected;
    }
    
    public final void setTabs(final Tab tab) {
        this.tabs.add(tab);
    }
    
    public String getSelectedTabName() {
        return this.selectedTabName;
    }
    
    public void setSelectedTabName(final String string) {
        this.selectedTabName = string;
    }
}
