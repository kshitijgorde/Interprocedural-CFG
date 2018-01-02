// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import pclient.shd.Config;
import java.awt.ScrollPane;

public class ExUserList extends UserList
{
    private SingleList userNames;
    private ScrollPane scrollPane;
    private Config paraConf;
    
    public ExUserList(final Config paraConf) {
        this.paraConf = paraConf;
        final ScrollPane scrollPane = new ScrollPane();
        this.userNames = new SingleList(0);
        this.setLayout(new BorderLayout());
        this.add("Center", scrollPane);
        this.add(scrollPane);
        scrollPane.add(this.userNames);
        this.scrollPane = scrollPane;
    }
    
    public void addSelectAction(final SelectAction selectAction) {
        this.userNames.addSelectAction(selectAction);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
    }
    
    public synchronized Dimension getPreferredSize() {
        return new Dimension(130, 160);
    }
    
    public synchronized void ignore(final int n) {
        this.userNames.setStrikeThrough(n, true);
    }
    
    public synchronized void stopIgnoring(final int n) {
        this.userNames.setStrikeThrough(n, false);
    }
    
    public boolean isIgnored(final int n) {
        return this.userNames.getStatus(n) == 2;
    }
    
    public synchronized void refresh() {
        this.userNames.refresh();
    }
    
    public void setFont(final Font font) {
        this.userNames.setFont(font);
        this.adjustScroll();
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
    
    public int getSelectedIndex() {
        return this.userNames.getSelectedIndex();
    }
    
    public synchronized void delItem(final int n) {
        this.userNames.delItem(n);
        this.adjustScroll();
    }
    
    public void delItems(final int n, final int n2) {
        this.userNames.delItems(n, n2);
        this.adjustScroll();
    }
    
    public String getItem(final int n) {
        return this.userNames.getItem(n);
    }
    
    public synchronized void addItem(final String s) {
        this.userNames.addItem(s);
        this.adjustScroll();
        this.printSize();
    }
    
    public synchronized void addItem(final String s, final int n) {
        this.userNames.addItem(s, n);
        this.adjustScroll();
    }
    
    public void deselect(final int n) {
        this.userNames.deselect(n);
    }
    
    public void setSize(final int n, final int n2) {
        this.userNames.setSize(n, n2);
        this.adjustScroll();
    }
    
    private void adjustScroll() {
        this.scrollPane.doLayout();
    }
    
    private void printSize() {
        this.getSize();
        this.scrollPane.getSize();
        this.userNames.getSize();
    }
    
    public void addDominantItem(final String s, final int n) {
        this.addColoredUser(s, n, this.paraConf.getPref().selfColor);
    }
    
    public void addSpeaker(final String s, final int n) {
        this.addColoredUser(s, n, this.paraConf.getPref().spkColor);
    }
    
    public void addModerator(final String s, final int n) {
        this.addColoredUser(s, n, this.paraConf.getPref().modColor);
    }
    
    public void addAdmin(final String s, int addColoredUser) {
        addColoredUser = this.addColoredUser(s, addColoredUser, this.paraConf.getPref().adminColor);
        final Font font = this.userNames.getFont();
        if (font == null) {
            return;
        }
        final Font font2 = new Font(font.getName(), 1, font.getSize());
        if (font2 == null) {
            return;
        }
        this.userNames.setItemFont(addColoredUser, font2);
    }
    
    private int addColoredUser(final String s, int n, final Color color) {
        this.addItem(s, n);
        final int countItems = this.countItems();
        if (n < 0 || n >= countItems) {
            n = countItems - 1;
        }
        this.userNames.setItemColor(n, color);
        this.adjustScroll();
        return n;
    }
}
