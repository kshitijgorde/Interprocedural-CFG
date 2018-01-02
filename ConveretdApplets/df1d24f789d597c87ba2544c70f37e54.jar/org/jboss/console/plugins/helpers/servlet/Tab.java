// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.helpers.servlet;

public class Tab
{
    private String name;
    private String href;
    private boolean selected;
    
    public Tab() {
    }
    
    public Tab(final String name, final String href, final boolean selected) {
        this.name = name;
        this.href = href;
        this.selected = selected;
    }
    
    public String getHref() {
        return this.href;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setHref(final String string) {
        this.href = string;
    }
    
    public void setName(final String string) {
        this.name = string;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public void setSelected(final boolean b) {
        this.selected = b;
    }
}
