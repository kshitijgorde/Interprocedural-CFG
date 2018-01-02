// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;

public abstract class UserList extends Panel
{
    public abstract void addSelectAction(final SelectAction p0);
    
    public abstract void setFont(final Font p0);
    
    public abstract void setForeground(final Color p0);
    
    public abstract void setBackground(final Color p0);
    
    public abstract int countItems();
    
    public abstract String getSelectedItem();
    
    public abstract void delItem(final int p0);
    
    public abstract void delItems(final int p0, final int p1);
    
    public abstract String getItem(final int p0);
    
    public abstract int getSelectedIndex();
    
    public abstract void addItem(final String p0);
    
    public abstract void addItem(final String p0, final int p1);
    
    public abstract void deselect(final int p0);
    
    public abstract void setSize(final int p0, final int p1);
    
    public abstract void ignore(final int p0);
    
    public abstract void stopIgnoring(final int p0);
    
    public abstract boolean isIgnored(final int p0);
    
    public abstract void refresh();
    
    public abstract void addDominantItem(final String p0, final int p1);
    
    public abstract void addSpeaker(final String p0, final int p1);
    
    public abstract void addModerator(final String p0, final int p1);
    
    public abstract void addAdmin(final String p0, final int p1);
}
