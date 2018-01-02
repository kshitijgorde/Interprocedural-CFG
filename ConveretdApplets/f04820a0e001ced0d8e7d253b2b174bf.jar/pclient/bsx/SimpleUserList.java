// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import pclient.bsc.SelectAction;
import java.awt.List;
import pclient.bsc.UserList;

public class SimpleUserList extends UserList
{
    private List userNames;
    private SelectAction action;
    
    public SimpleUserList() {
        this.userNames = new List(0, false);
        this.setLayout(new BorderLayout());
        this.add("Center", this.userNames);
    }
    
    public void addSelectAction(final SelectAction action) {
        this.action = action;
    }
    
    public void ignore(final int n) {
    }
    
    public void stopIgnoring(final int n) {
    }
    
    public void refresh() {
    }
    
    public int getSelectedIndex() {
        return this.userNames.getSelectedIndex();
    }
    
    public boolean isIgnored(final int n) {
        return false;
    }
    
    public void setFont(final Font font) {
        this.userNames.setFont(font);
    }
    
    public void setForeground(final Color foreground) {
        this.userNames.setForeground(foreground);
    }
    
    public void setBackground(final Color background) {
        this.userNames.setBackground(background);
    }
    
    public int countItems() {
        return this.userNames.countItems();
    }
    
    public String getSelectedItem() {
        return this.userNames.getSelectedItem();
    }
    
    public void delItem(final int n) {
        this.userNames.delItem(n);
    }
    
    public void delItems(final int n, final int n2) {
        this.userNames.delItems(n, n2);
    }
    
    public String getItem(final int n) {
        return this.userNames.getItem(n);
    }
    
    public void addItem(final String s) {
        this.userNames.addItem(s);
    }
    
    public void addItem(final String s, final int n) {
        this.userNames.addItem(s, n);
    }
    
    public void deselect(final int n) {
        this.userNames.deselect(n);
    }
    
    public void setSize(final int n, final int n2) {
        this.userNames.setSize(n, n2);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.userNames && event.id == 701) {
            if (this.action != null) {
                this.action.clickAction();
            }
            return false;
        }
        return super.handleEvent(event);
    }
    
    public void addDominantItem(final String s, final int n) {
        this.addItem(s, n);
    }
    
    public void addSpeaker(final String s, final int n) {
        this.addItem(s, n);
    }
    
    public void addModerator(final String s, final int n) {
        this.addItem(s, n);
    }
    
    public void addAdmin(final String s, final int n) {
        this.addItem(s, n);
    }
}
