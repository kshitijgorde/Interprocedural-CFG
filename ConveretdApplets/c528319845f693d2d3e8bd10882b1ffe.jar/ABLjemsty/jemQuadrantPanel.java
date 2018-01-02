// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import java.awt.Image;
import ABLwidgets.pen;
import java.awt.Color;
import ABLwidgets.quadrant;

public class jemQuadrantPanel extends quadrant
{
    public int a;
    public int b;
    public Color c;
    public Color d;
    public Color e;
    public Color f;
    public Color g;
    public Color h;
    public Color i;
    public Color j;
    public Color k;
    public Color l;
    public Color m;
    public Color n;
    public char o;
    public char p;
    public char q;
    public char r;
    public int s;
    public int t;
    public String u;
    public String v;
    public char w;
    public String x;
    public String y;
    public int z;
    public char aa;
    public double ab;
    public char ac;
    public char ad;
    public char ae;
    public String[] af;
    public String[] ag;
    public char ah;
    public char ai;
    public jemTabPanel aj;
    public jemTabPanel ak;
    public jemTabPanel al;
    public jemTabPanel am;
    public jemTabPanel an;
    public EmuPanelVector ao;
    public int ap;
    public String aq;
    public boolean ar;
    
    public jemQuadrantPanel() {
        this.o = '0';
        this.p = '0';
        this.q = '0';
        this.r = '0';
        this.ah = '0';
    }
    
    public jemQuadrantPanel(final pen pen, final Image image, final int n, final int n2, final int n3, final int n4) {
        super(pen, image, n, n2, n3, n4);
        this.o = '0';
        this.p = '0';
        this.q = '0';
        this.r = '0';
        this.ah = '0';
    }
    
    public int d() {
        return this.getMinimumSize().width;
    }
    
    public int e() {
        return this.getMinimumSize().height;
    }
}
