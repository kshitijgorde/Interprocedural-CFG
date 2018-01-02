// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers.servlet;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class TabTag extends TagSupport
{
    private String name;
    private String href;
    private boolean selected;
    private NavigationTag myParent;
    
    public TabTag() {
        this.myParent = null;
    }
    
    public int doStartTag() throws JspTagException {
        this.myParent = (NavigationTag)findAncestorWithClass((Tag)this, (Class)NavigationTag.class);
        if (this.myParent == null) {
            throw new JspTagException("Tab tag not nested within navigation tag.");
        }
        if (this.name != null) {
            this.addToParent();
        }
        return 1;
    }
    
    private void addToParent() {
        final Tab tab = new Tab(this.name, this.href, this.selected);
        this.myParent.setTabs(tab);
    }
    
    public String getHref() {
        return this.href;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public void setHref(final String string) {
        this.href = string;
    }
    
    public void setName(final String string) {
        this.name = string;
    }
    
    public void setSelected(final boolean b) {
        this.selected = b;
    }
}
