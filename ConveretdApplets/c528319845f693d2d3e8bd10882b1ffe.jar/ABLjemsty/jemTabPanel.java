// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import java.awt.Graphics;
import ABLwidgets.tab;

public class jemTabPanel extends tab
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public EmuPanel k;
    public char l;
    
    public jemTabPanel(final int i, final char c, final char c2) {
        super(c, c2);
        this.l = '0';
        this.i = i;
    }
    
    public void update(final Graphics graphics) {
        if (this.g == 0) {
            return;
        }
        super.update(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.g == 0) {
            return;
        }
        super.paint(graphics);
    }
}
