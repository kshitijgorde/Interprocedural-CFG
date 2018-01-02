// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import geracemenu.parser.IntValue;
import geracemenu.parser.Type;
import java.awt.Insets;
import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.Dimension;

public class SeparatorItem extends MenuItem
{
    private int barHeight;
    
    public boolean isSelectable() {
        return false;
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(super.insets.left + this.getWidth() + super.insets.right, super.insets.top + 2 + super.insets.bottom);
    }
    
    public void paintItem(final Graphics graphics) {
        final int n = this.barHeight / 2;
        graphics.setColor(this.getBackground().darker());
        graphics.fillRect(super.insets.left, super.insets.top, this.getWidth(), n);
        graphics.setColor(this.getBackground().brighter());
        graphics.fillRect(super.insets.left, super.insets.top + n, this.getWidth(), n);
    }
    
    public String toString() {
        return "This is SeparatorItem";
    }
    
    public SeparatorItem() {
        this((Hashtable)null);
    }
    
    public SeparatorItem(final Hashtable hashtable) {
        super(hashtable);
        this.barHeight = 2;
        super.insets = new Insets(0, 4, 4, 0);
        final Type type;
        if (hashtable != null && (type = hashtable.get("height")) != null) {
            this.barHeight = (int)((IntValue)type).getValue();
        }
    }
}
