// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

import java.util.Vector;

public class DrawResult
{
    public Vector updateHandles;
    public StyledRectangle rectangle;
    public DrawResultItem[] items;
    
    public String toString() {
        String string = "";
        for (int i = 0; i < this.items.length; ++i) {
            string += this.items[i].originalstrippedword;
        }
        return string;
    }
}
