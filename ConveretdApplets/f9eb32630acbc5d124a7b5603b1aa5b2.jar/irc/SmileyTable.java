// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.Image;
import java.util.Vector;

public class SmileyTable
{
    private Vector _table;
    
    public SmileyTable() {
        this._table = new Vector();
    }
    
    public void addSmiley(final String s, final Image image) {
        if (image != null) {
            this._table.insertElementAt(new SmileyItem(s, image), this._table.size());
        }
    }
    
    public int getSize() {
        return this._table.size();
    }
    
    public String getMatch(final int n) {
        return this._table.elementAt(n).match;
    }
    
    public Image getImage(final int n) {
        if (n < 0) {
            return null;
        }
        if (n >= this.getSize()) {
            return null;
        }
        return this._table.elementAt(n).img;
    }
}
