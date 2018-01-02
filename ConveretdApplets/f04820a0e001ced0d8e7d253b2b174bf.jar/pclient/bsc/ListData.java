// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.Font;
import java.awt.Color;

public class ListData
{
    public String item;
    public int length;
    public Color color;
    public Color background;
    public boolean isSelected;
    public int state;
    public Font font;
    public int startX;
    public int startY;
    public int endX;
    public int endY;
    public int baseline;
    public boolean isItalic;
    public static final int NORMAL = 1;
    public static final int STRIKE_THROUGH = 2;
    
    public ListData() {
        this.isItalic = false;
        this.state = 1;
        this.isSelected = false;
    }
    
    public void printData() {
    }
}
